package org.voyo.utils.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.voyo.utils.utils.YoDate;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class YoJackson {


  static class DateDeserializer extends DateDeserializers.DateDeserializer{
    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      final String date=p.getText();
      try {
        return YoDate.parseDateStr(date, YoDate.BASE_TIME_FORMAT);
      }catch (final ParseException e){
        return this._parseDate(p, ctxt);
      }
    }
  }

  public static Module createModule(){
    SimpleModule module=new SimpleModule();
    module.addSerializer(Date.class,new DateSerializer(false, new SimpleDateFormat(YoDate.BASE_TIME_FORMAT)));
    module.addDeserializer(Date.class,new DateDeserializer());
    module.addSerializer(Long.class, ToStringSerializer.instance);
    module.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(YoDate.BASE_DATE_FORMAT)));
    module.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(YoDate.BASE_DATE_FORMAT)));
    return module;
  }
}
