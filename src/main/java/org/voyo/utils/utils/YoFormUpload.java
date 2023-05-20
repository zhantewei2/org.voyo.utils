package org.voyo.utils.utils;

import lombok.Data;
import lombok.SneakyThrows;

import javax.net.ssl.HttpsURLConnection;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

public class YoFormUpload {
  final static String newLine="\r\n";
  final static Integer chunkSizeDefault=1024*300;
  final static String defaultMime=YoMime.MIME_STREAM;

  @Data
  public static class HttpResult{
    private Integer statusCode;
    private InputStream data;
  }
  @Data
  public static class ReqParamsFile{
    //文件于formData中的key
    private String key;
    //文件名 required
    private String name;
    //文件mime required, 如 "image/png" | "text/plain", 不填时默认为 "application/octet-stream"
    private String mime;
    //文件流 required
    private InputStream inputStream;
    //分块上传大小， 默认 1024*300 byte
    private Integer chunkSize;
  }
  @Data
  public static class ReqParams{
    private String url;
    private String method;
    //formData参数
    private Map<String,String> formData;
    private ReqParamsFile file;
    //url参数
    private Map<String,Object> query;

  }

  @SneakyThrows
  public HttpResult streamUpload(ReqParams params, InputStream ins){
    String url=params.getUrl();
    Map<String,String> formData=params.getFormData();
    ReqParamsFile file=params.getFile();

    HttpURLConnection conn=(HttpsURLConnection) new URL(url).openConnection();
    String boundary="----"+new Date().getTime();

    conn.setRequestProperty("Content-Type","multipart/form-data; boundary="+boundary);
    conn.setRequestMethod("POST");
    conn.setDoOutput(true);
    conn.setChunkedStreamingMode(0);
    conn.connect();;
    OutputStream ops=conn.getOutputStream();
    boundary="--"+boundary;

    //formData
    if(formData!=null){
      StringBuilder paramsBuilder=new StringBuilder();
      for(Map.Entry<String,String> entry: formData.entrySet()){
        paramsBuilder.append(formDataPropertyVal(boundary,entry.getKey(),entry.getValue()));
      }
      ops.write(paramsBuilder.toString().getBytes());
    }
    //file
    if(file!=null){
      formDataWriteFile(boundary,ops,file.getKey(),file.getName(),file.getMime(),ins);
    }

    int statusCode=conn.getResponseCode();
    YoIO.closeQuietly(ops);
    HttpResult result=new HttpResult();
    result.setStatusCode(statusCode);
    result.setData(conn.getInputStream());
    return result;
  }
  private String formDataPropertyVal(String boundary,String key,String val){
    return boundary+newLine
      +"Content-Disposition: form-data; name=\""+key+"\"" +newLine
      +newLine
      +val+newLine;
  }
  private void formDataWriteFile(String boundary,OutputStream ops,String key,String fileName,String fileMime,InputStream fileStream)throws Exception {
    fileMime= (fileMime==null||"".equals(fileMime))? defaultMime:fileMime;
    String prefix= boundary+newLine
      + "Content-Disposition: form-data; name=\""+key+"\"; "+"filename=\""+fileName+"\""+newLine
      + "Content-Type: "+fileMime+newLine
//      + "Content-Transfer-Encoding: binary"+newLine
      + newLine;

    ops.write(prefix.getBytes());
    int readSize=0;
    byte[] chunk=new byte[1024*300];
    showMem();
    int count=0;
    while((readSize=fileStream.read(chunk))>0){
      ops.write(chunk,0,readSize);
      count++;
      showMem();

    }
    System.out.println("total:"+count);
    YoIO.closeQuietly(fileStream);

    ops.write((boundary+"--").getBytes());
  }

  private void showMem(){
    double totalMem=Runtime.getRuntime().totalMemory()/ 1024/1024;
    System.out.println("totalMem:"+totalMem);
  }
}
