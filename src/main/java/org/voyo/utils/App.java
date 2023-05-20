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

    System.setProperty("http.proxyHost","127.0.0.1");
    System.setProperty("http.proxyPort","8888");
    System.setProperty("http.ProxySet","true");

    YoFormUpload yoHttp=new YoFormUpload();
    Path p=Paths.get("C:\\Users\\zhantewei\\Desktop\\1.png");
    FileInputStream fileInputStream=new FileInputStream(p.toFile());
    YoFormUpload.FormUploadParams params=new YoFormUpload.FormUploadParams();
//    params.setUrl("http://127.0.0.1:6001/test/test");
    params.setUrl("http://121.41.58.238/proxy-corp/cgi-bin/media/upload_attachment");
    params.setMethod("POST");
    params.setQuery(new HashMap<String,Object>(){{
      put("access_token","xYIv2P13SjlWmJErSR6Zw_JfAgO_zUQr-1WXVhs84qDqk2-seXh9QltGP_hQm3c3522AhzffA6u8P1Ua-yNCo-wPpApCw8TQA35sWjBYp8zQaQbBSF4UPL6wQconL-P2Co_fzolz3iJ3gMooUPwjxH_LTNPJrhgMPzGZY_BK8cO3DK_ggb66zkvd1TlO9we7PdnYi6vKzrWmvlww96DQ4w");
      put("media_type","image");
      put("attachment_type","1");
    }});
    YoFormUpload.ReqParamsFile file=new YoFormUpload.ReqParamsFile();
    file.setName("name1.png");
    file.setKey("media");
    file.setInputStream(fileInputStream);
    file.setLength(12);
    params.setFile(file);

    YoFormUpload.HttpResult r=yoHttp.streamUpload(params);
    String resultContent=YoIO.readStreamAsStr(r.getData());

    System.out.println(resultContent);

  }


}
