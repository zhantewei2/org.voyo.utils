import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.voyo.utils.utils.YoObject;

@Slf4j
public class tt {

  @SneakyThrows
  public static void main(String[] args){
    String json= "{\"name\": \"  ztwx\",\"age\":2}";


    TtDto t=YoObject.loadJson(json,TtDto.class);
    log.info("t:{}",YoObject.toJson(t));
  }
}
