package personal.ng.MongoCodecs;

import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import personal.ng.Models.Dto;

public class DtoCodecProvider implements CodecProvider {

    public DtoCodecProvider() {}

    @Override
    @SuppressWarnings("unchecked")
    public <T> Codec<T> get(Class<T> aClass, CodecRegistry codecRegistry) {

        if (aClass == Dto.class) {
            return (Codec<T>) new DtoCodec(codecRegistry);
        }
        return null;
    }


}
