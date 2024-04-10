package org.voyo.utils.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import org.voyo.utils.utils.YoStr;

import java.io.IOException;

public class LikeCharacterFieldDeserializer extends JsonDeserializer<String> implements ContextualDeserializer {
    @Override
    public String deserialize(JsonParser parser, DeserializationContext ctx) {
        try{
            String val=parser.getText();
            return YoStr.isBlank(val)? val : val.replace("\\", "\\\\").replace("%", "\\%").replace("_", "\\_");
        }catch (IOException e){
            return null;
        }
    }

    public JsonDeserializer<?> createContextual(DeserializationContext provider, BeanProperty bean) throws JsonMappingException {
        return this;
    }
}
