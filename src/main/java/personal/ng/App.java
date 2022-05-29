package personal.ng;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.*;
import org.bson.BsonDocument;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.ClassModel;
import org.bson.codecs.pojo.Convention;
import org.bson.codecs.pojo.Conventions;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.json.JsonObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import personal.ng.Models.Comment;
import personal.ng.Models.Dto;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.stream.Collectors;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class App
{
    public static CodecProvider pojoCodecProvider;
    public static CodecRegistry pojoCodecRegistry;

    public static void main( String[] args ) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        File dataFile = new File(Configuration.inputData);
        MongoClient mongoClient = connect();
        int objectCount = 0;

        //Conversion Possibilities
        BsonDocument bsonUsingJsonDataClass = dtoToBsonUsingJsonDataClass(dataFile);
        Dto dtoUsingAutomaticConversion = bsonToDto();

        try
        {
            MappingIterator<Dto> it = objectMapper.readerFor(Dto.class).readValues(dataFile);
            MongoDatabase db = mongoClient.getDatabase(Configuration.db).withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Dto> coll = db.getCollection(Configuration.coll, Dto.class);
            MongoCursor<Dto> docs = coll.find().cursor();
            while(docs.hasNext()) {
                Dto dto = docs.next();
                System.out.println(dto);
            }
            while (it.hasNextValue()) {
                Dto dto = it.nextValue();
//                MongoDoc doc = DtoToMongodocConverter.convert(dto);

                objectCount++;
                System.out.println("Processed ["+objectCount+"] records so far."); //Test
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error iterating through file ["+dataFile+"].");
            ex.printStackTrace();
        }
        finally {
            mongoClient.close();
        }
    }

    @NotNull
    private static Dto bsonToDto() {
        ClassModel<Dto> dtoPojoModel = ClassModel.builder(Dto.class).conventions(Arrays.asList(Conventions.ANNOTATION_CONVENTION)).build();
        ClassModel<Comment> commentPojoModel = ClassModel.builder(Comment.class).conventions(Arrays.asList(Conventions.ANNOTATION_CONVENTION)).build();
        PojoCodecProvider localPojoCodecProvider = PojoCodecProvider.builder().register(dtoPojoModel,commentPojoModel).build();
        CodecRegistry localPojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(localPojoCodecProvider));

        MongoClient mongoClient = connect();
        MongoDatabase db = mongoClient.getDatabase(Configuration.db).withCodecRegistry(localPojoCodecRegistry);
        MongoCollection<Dto> coll = db.getCollection(Configuration.coll, Dto.class);
        MongoCursor<Dto> docs = coll.find().cursor();
        Dto dto = docs.next();
        mongoClient.close();
        return dto;
    }

    private static BsonDocument dtoToBsonUsingJsonDataClass(@NotNull File dataFile) throws IOException {
        String strData = Files.lines(dataFile.toPath()).collect(Collectors.joining(""));
        JsonObject jsonData = new JsonObject(strData);
        BsonDocument bsonDoc = jsonData.toBsonDocument(Dto.class, pojoCodecRegistry);
        return bsonDoc;
    }

    @Nullable
    private static MongoClient connect() {
        pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        ConnectionString connectionString = new ConnectionString(Configuration.connectionString);
        MongoClientSettings settings = MongoClientSettings.builder()
            .codecRegistry(pojoCodecRegistry)
            .applyConnectionString(connectionString)
            .serverApi(ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build())
            .build();

        MongoClient mongoClient = null;
        try {
            mongoClient = MongoClients.create(settings);
        }
        catch(Exception ex) {
            System.out.println("Error connecting to MongoDB Atlas.");
        }

        return mongoClient;
    }

//        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().register("com.mongodb.pov.TargetModelV2").build();
//        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
//
//        CodecRegistry registry = getDefaultCodecRegistry();
////        CodecRegistry pojoCodecRegistry = org.bson.codecs.configuration.CodecRegistries.fromRegistries(getDefaultCodecRegistry()
////                , org.bson.codecs.configuration.CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
//
//

//        database = mongoClient.getDatabase(Configuration.db).withCodecRegistry(pojoCodecRegistry);
//        collection = database.getCollection(Configuration.coll, TargetModelV2.class);
//    }
}
