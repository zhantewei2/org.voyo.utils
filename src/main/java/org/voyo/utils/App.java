package org.voyo.utils;
import lombok.SneakyThrows;
import org.voyo.utils.utils.YoFormUpload;
import org.voyo.utils.utils.YoIO;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class App {
  @SneakyThrows
  public static void main(String[] args){
    YoFormUpload yoHttp=new YoFormUpload();
    Path p=Paths.get("C:\\Users\\zhantewei\\Desktop\\123.txt");
    FileInputStream fileInputStream=new FileInputStream(p.toFile());
    YoFormUpload.FormUploadParams params=new YoFormUpload.FormUploadParams();
    params.setUrl("http://localhost:3000");
    params.setMethod("POST");
    params.setFormData(new HashMap<String,String>(){{
      put("name","name");
    }});
    YoFormUpload.ReqParamsFile file=new YoFormUpload.ReqParamsFile();
    file.setName("name1");
    file.setKey("file");
    file.setInputStream(fileInputStream);
    params.setFile(file);

    YoFormUpload.HttpResult r=yoHttp.streamUpload(params);
    String resultContent=YoIO.readStreamAsStr(r.getData());

    System.out.println(resultContent);

  }


}
