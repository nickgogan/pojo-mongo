package personal.ng.MongoCodecs;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;
import personal.ng.Models.Dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DtoCodec implements Codec<Dto> {

    Codec<String> strCodec;
    Codec<String> permalink;
    Codec<String> author;
    Codec<String> title;
    Codec<List> tags;
    Codec<ArrayList> comments;
    Codec<Date> date;

    public DtoCodec(CodecRegistry codecRegistry) {
        this.strCodec = codecRegistry.get(String.class);
        this.permalink = codecRegistry.get(String.class);
        this.author = codecRegistry.get(String.class);
        this.title = codecRegistry.get(String.class);
        this.tags = codecRegistry.get(List.class);
        this.comments = codecRegistry.get(ArrayList.class);
        this.date = codecRegistry.get(Date.class);
    }

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
