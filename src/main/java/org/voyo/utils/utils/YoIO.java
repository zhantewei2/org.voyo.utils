package org.voyo.utils.utils;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

public class YoIO {
  public static void closeQuietly(Closeable closeable){
    try{
      if(closeable!=null)closeable.close();
    }catch (IOException e){}
  }

  public static String readStreamAsStr(InputStream inputStream) {
    ByteArrayOutputStream buffer=new ByteArrayOutputStream();
    try {
      int chunkSize = 1024*5;
      byte[] chunk = new byte[chunkSize];
      int readn;
      while ((readn = inputStream.read(chunk, 0, chunkSize)) > 0) {
        buffer.write(chunk, 0, readn);
      }
      inputStream.close();
      buffer.close();
      return new String(buffer.toByteArray());
    }catch (Exception e){
      e.printStackTrace();
      return null;
    }finally {
      closeQuietly(inputStream);
      closeQuietly(buffer);
    }
  }

  public static <T> T readStreamAsJson(InputStream inputStream,Class<T> classType){
    String content=readStreamAsStr(inputStream);
    return YoObject.loadJson(content,classType);
  }
}
