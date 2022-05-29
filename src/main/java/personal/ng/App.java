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
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.ClassModel;
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
    static CodecRegistry pojoCodecRegistry;
    static MongoClient mongoClient;

    public static void main( String[] args ) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        File dataFile = new File(Configuration.inputData);

        //Setup up MongoDB conversion
        getPojoCodecRegistry();

        //TODO: Writing

        //Reading from local disk to Dto using the Jackson library
        MappingIterator<Dto> it = localJsonToDtoUsingJackson(objectMapper, dataFile);
        while(it.hasNext()) {
            Dto doc = it.next();
            System.out.println(doc.toString());
        }

        //Reading from local disk to Dto and then to Bson using MongoDB JsonData class intermediary
        BsonDocument bsonUsingJsonDataClass = localJsonToDtoBsonUsingJsonDataClass(dataFile);

        //Reading from MongoDB Atlas
        MongoCollection<Dto> dtoUsingClassModelBuilders = bsonToDtoUsingClassModelBuilders();
        MongoCursor<Dto> cursorDtoDocs = dtoUsingClassModelBuilders.find().cursor();
        while(cursorDtoDocs.hasNext())
        {
            Dto doc = cursorDtoDocs.next();
            System.out.println(doc);
        }
        mongoClient.close();
    }

    private static MappingIterator<Dto> localJsonToDtoUsingJackson(@NotNull ObjectMapper objectMapper, @NotNull File dataFile) throws IOException {
        return objectMapper.readerFor(Dto.class).readValues(dataFile);
    }

    @NotNull
    private static MongoCollection<Dto> bsonToDtoUsingClassModelBuilders() {
        MongoClient mongoClient = connect(getPojoCodecRegistry());
        MongoDatabase db = mongoClient.getDatabase(Configuration.db).withCodecRegistry(getPojoCodecRegistry());
        MongoCollection<Dto> collection = db.getCollection(Configuration.coll, Dto.class);
        return collection;
    }

    private static BsonDocument localJsonToDtoBsonUsingJsonDataClass(@NotNull File dataFile) throws IOException {
        String strData = Files.lines(dataFile.toPath()).collect(Collectors.joining(""));
        JsonObject jsonData = new JsonObject(strData);
        BsonDocument bsonDoc = jsonData.toBsonDocument(Dto.class, getPojoCodecRegistry());
        return bsonDoc;
    }

    private static CodecRegistry getPojoCodecRegistry() {
        if(App.pojoCodecRegistry == null) {
            createPojoCodecRegistry();
            return App.pojoCodecRegistry;
        }
        return App.pojoCodecRegistry;
    }

    @NotNull
    private static void createPojoCodecRegistry() {
        ClassModel<Dto> dtoPojoModel = ClassModel.builder(Dto.class).conventions(Arrays.asList(Conventions.ANNOTATION_CONVENTION)).build();
        ClassModel<Comment> commentPojoModel = ClassModel.builder(Comment.class).conventions(Arrays.asList(Conventions.ANNOTATION_CONVENTION)).build();
        PojoCodecProvider localPojoCodecProvider = PojoCodecProvider.builder().register(dtoPojoModel,commentPojoModel).build(); //Register the class model instead of calling automatic(true) before the build().
        pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(localPojoCodecProvider));
    }

    @Nullable
    private static MongoClient connect(CodecRegistry pojoCodecRegistry) {
        ConnectionString connectionString = new ConnectionString(Configuration.connectionString);
        MongoClientSettings settings = MongoClientSettings.builder()
            .codecRegistry(pojoCodecRegistry)
            .applyConnectionString(connectionString)
            .serverApi(ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build())
            .build();

        try {
            mongoClient = MongoClients.create(settings);
        }
        catch(Exception ex) {
            System.out.println("Error connecting to MongoDB Atlas.");
        }

        return mongoClient;
    }
}
