package personal.ng.MongoCodecs;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import personal.ng.Models.Dto;

public class DtoCodec implements Codec<Dto> {

    @Override
    public Dto decode(BsonReader bsonReader, DecoderContext decoderContext) {
        return null;
    }

    @Override
    public void encode(BsonWriter bsonWriter, Dto dto, EncoderContext encoderContext) {

    }

    @Override
    public Class<Dto> getEncoderClass() {
        return null;
    }
}
