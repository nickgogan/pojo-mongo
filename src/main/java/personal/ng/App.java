package personal.ng;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mongodb.client.*;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.io.File;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class App
{
    public static void main( String[] args )
    {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        MongoClient mongoClient = null;
        MongoDatabase db = null;
        File dataFile = new File(Configuration.inputData);
        int objectCount = 0;

        try
        {
            MappingIterator<Dto> it = objectMapper.readerFor(Dto.class).readValues(dataFile);
            mongoClient = MongoClients.create(Configuration.connectionString);
            db = mongoClient.getDatabase(Configuration.db).withCodecRegistry(pojoCodecRegistry);
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

//    private void connect() {
//        ConnectionString connectionString = new ConnectionString(Configuration.connectionString);
//
//        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().register("com.mongodb.pov.TargetModelV2").build();
//        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
//
//        CodecRegistry registry = getDefaultCodecRegistry();
////        CodecRegistry pojoCodecRegistry = org.bson.codecs.configuration.CodecRegistries.fromRegistries(getDefaultCodecRegistry()
////                , org.bson.codecs.configuration.CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
//
//
//        MongoClientSettings settings = MongoClientSettings.builder()
//                .codecRegistry(registry)
//                .applyConnectionString(connectionString)
//                .serverApi(ServerApi.builder()
//                        .version(ServerApiVersion.V1)
//                        .build())
//                .build();
//        mongoClient = MongoClients.create(settings);
//        database = mongoClient.getDatabase(Configuration.db).withCodecRegistry(pojoCodecRegistry);
//        collection = database.getCollection(Configuration.coll, TargetModelV2.class);
//    }
}
