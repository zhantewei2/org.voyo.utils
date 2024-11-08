import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.voyo.utils.jackson.format.Trim;

@Setter
@Getter
//@NoArgsConstructor
public class TtDto {

  private Integer age;
  @Trim
  private String name;

}
