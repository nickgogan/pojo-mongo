package personal.ng.MongoCodecs;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import personal.ng.Models.Comment;

public class CommentCodec implements Codec<Comment> {

    @Override
    public Comment decode(BsonReader bsonReader, DecoderContext decoderContext) {
        return null;
    }

    @Override
    public void encode(BsonWriter bsonWriter, Comment comment, EncoderContext encoderContext) {

    }

    @Override
    public Class<Comment> getEncoderClass() {
        return null;
    }
}
