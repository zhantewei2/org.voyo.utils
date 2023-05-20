package org.voyo.utils.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class YoMime {
  public static final String MIME_JPG="image/jpg";
  public static final String MIME_JPEG="image/jpeg";
  public static final String MIME_BMP="image/bmp";
  public static final String MIME_GIF="image/gif";
  public static final String MIME_ICO="image/vnd.microsoft.icon";
  public static final String MIME_PNG="image/png";
  public static final String MIME_SVG="image/svg+xml";
  public static final String MIME_WEBP="image/webp";

  public static final String MIME_STREAM= "application/octet-stream";

  public static List<String> imageMime=Arrays.asList(
    MIME_JPG,MIME_BMP,MIME_GIF,MIME_ICO,MIME_PNG,MIME_SVG,MIME_WEBP,MIME_JPEG
  );
  public static HashMap<String,String> extMapping;
  public static boolean isImageMime(String mime){
    return imageMime.contains(mime);
  }

  static {
       extMapping=new HashMap<>();
       extMapping.put(MIME_JPG,"jpg");
       extMapping.put(MIME_BMP,"bmp");
       extMapping.put(MIME_GIF,"gif");
       extMapping.put(MIME_ICO,"ico");
       extMapping.put(MIME_PNG,"png");
       extMapping.put(MIME_SVG,"svg");
       extMapping.put(MIME_WEBP,"webp");
       extMapping.put(MIME_JPEG,"jpg");
  }

  public static String getExtByMime(String mimeType){
    return extMapping.get(mimeType.toLowerCase());
  }
}