package org.voyo.utils.utils;

import lombok.Data;
import lombok.SneakyThrows;
import org.voyo.utils.utils.url.YoUrl;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
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
    //filelength
    private Integer length;
  }
  @Data
  public static class FormUploadParams{
    private String url;
    private String method;
    //formData参数  00
    private Map<String,String> formData;
    private ReqParamsFile file;
    //url参数
    private Map<String,Object> query;

  }

   /* example:
      *     YoFormUpload.FormUploadParams params=new YoFormUpload.FormUploadParams();
   *     params.setUrl("http://localhost:3000");
   *     params.setMethod("POST");
   *     params.setFormData(new HashMap<String,String>(){{
   *       put("name","name");
   *     }});
   *     YoFormUpload.ReqParamsFile file=new YoFormUpload.ReqParamsFile();
   *     file.setName("name1");
   *     file.setKey("file");
   *     file.setInputStream(fileInputStream);
   *     params.setFile(file);
   *
   *     YoFormUpload.HttpResult r=yoHttp.streamUpload(params);
   *     String resultContent=YoIO.readStreamAsStr(r.getData());
   *
   */
  /**
   * 流示上传，可边下载边上传。
   * @param params
   * @return http result
  */
  @SneakyThrows
  public HttpResult streamUpload(FormUploadParams params){
    String url=params.getUrl();
    Map<String,String> formData=params.getFormData();
    ReqParamsFile file=params.getFile();


    String boundary="-------------------------"+new Date().getTime();
    HttpURLConnection conn=buildConn(params,boundary);
    conn.connect();
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
      formDataWriteFile(boundary,ops,file);
    }
    //这里结尾其实可以不加newLine，部分解析框架需要加newLine,比如企微
    ops.write((boundary+"--"+newLine).getBytes());
    int statusCode=conn.getResponseCode();
    YoIO.closeQuietly(ops);
    HttpResult result=new HttpResult();
    result.setStatusCode(statusCode);
    result.setData(conn.getInputStream());
    return result;
  }
  private HttpURLConnection buildConn(FormUploadParams reqParams,String boundary)throws MalformedURLException, IOException {
    String url=reqParams.getUrl();
    Map<String,Object> query=reqParams.getQuery();

    if(query!=null)url+="?"+ YoUrl.encodeQuery(query);

    HttpURLConnection conn=null;
//    Proxy proxy=new Proxy(Proxy.Type.HTTP,new InetSocketAddress("127.0.0.1",8888));
    if(url.startsWith("https")){
      conn=(HttpsURLConnection) new URL(url).openConnection();
    }else{
      conn=(HttpURLConnection) new URL(url).openConnection();
    }
    conn.setRequestProperty("Content-Type","multipart/form-data; boundary="+boundary);
    conn.setRequestMethod(reqParams.getMethod().toUpperCase());
    conn.setDoOutput(true);
//    conn.setChunkedStreamingMode(100);
    return conn;
  }

  private String formDataPropertyVal(String boundary,String key,String val){
    return boundary+newLine
      +"Content-Disposition: form-data; name=\""+key+"\"" +newLine
      +newLine
      +val+newLine;
  }
  private void formDataWriteFile(String boundary,OutputStream ops,ReqParamsFile file)throws Exception {
    Integer chunkSize=file.getChunkSize()==null? chunkSizeDefault: file.getChunkSize();
    String fileMime= YoStr.isBlank(file.getMime())? defaultMime: file.getMime();
    InputStream fileStream=file.getInputStream();
    String prefix= boundary+newLine
      + "Content-Disposition: form-data; name=\"" + file.getKey()
      + "\"; "+"filename=\""+file.getName()+"\""
      + (file.getLength()!=null?"; filelength="+file.getLength():"")
      + newLine
      + "Content-Type: "+fileMime+newLine
      + "Content-Transfer-Encoding: binary"+newLine
      + newLine;

    ops.write(prefix.getBytes());
    int readSize=0;
    byte[] chunk=new byte[chunkSize];
    int count=0;
    while((readSize=fileStream.read(chunk))>0){
      ops.write(chunk,0,readSize);
      count++;
    }
    ops.write(newLine.getBytes());
    YoIO.closeQuietly(fileStream);
  }
}
