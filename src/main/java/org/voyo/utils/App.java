package org.voyo.utils;

import lombok.SneakyThrows;
import org.voyo.utils.utils.YoFormUpload;
import org.voyo.utils.utils.YoIO;

import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;


public class App {
  @SneakyThrows
  public static void main(String[] args){
    YoFormUpload yoHttp=new YoFormUpload();
    Path p=Paths.get("D:\\xfmovie\\1.mp4");
    FileInputStream fileInputStream=new FileInputStream(p.toFile());



    YoFormUpload.HttpResult r=yoHttp.send("http://localhost:3000",fileInputStream);
    String resultContent=YoIO.readStreamAsStr(r.getData());

    System.out.println(resultContent);
  }
}
