package personal.ng.JacksonHelpers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import personal.ng.Configuration;

public class StringDeserializer extends JsonDeserializer<String> {
    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        try {
            String inputText = jsonParser.getText();
            for(int i = 0; i < Configuration.valueFilters.length; i++)
            {
                if(Configuration.valueFilters[i].equals(inputText)) {
                    return null;
                }
            }
            return inputText;
        } catch(Exception ex) {
            System.out.println("[GeneralDeserializer.deserialize] Generic exception, please investigate.");
            ex.printStackTrace();
            return null;
        }
    }
}
