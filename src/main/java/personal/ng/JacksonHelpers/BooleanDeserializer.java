package personal.ng.JacksonHelpers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.jetbrains.annotations.NotNull;
import personal.ng.Configuration;

import java.io.IOException;

public class BooleanDeserializer extends JsonDeserializer<Boolean> {
    @Override
    public Boolean deserialize(@NotNull JsonParser jsonParser, DeserializationContext ctxt)
            throws IOException {
        String inputText = jsonParser.getText();
        if(inputText == null || inputText.isEmpty()) {
            return false;
        }
        for(int i = 0; i < Configuration.valueFilters.length; i++)
        {
            if(Configuration.valueFilters[i].equals(inputText)) {
                return false;
            }
        }

        if ("Y".equals(inputText)) {
            return Boolean.TRUE;
        }
        if ("N".equals(inputText)) {
            return Boolean.FALSE;
        }
        return null;
    }
}
