package personal.ng.JacksonHelpers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class BooleanSerializer extends JsonSerializer<Boolean> {
    @Override
    public void serialize(Boolean aBoolean, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if(aBoolean == null) {
            jsonGenerator.writeString(Boolean.FALSE.toString());
        }
        else {
            jsonGenerator.writeString(aBoolean.toString());
        }
    }
}
