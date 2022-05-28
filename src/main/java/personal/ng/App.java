package personal.ng;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;

public class App
{
    public static void main( String[] args )
    {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        ObjectMapper mapper = new ObjectMapper();

        File dataFile = new File(Configuration.inputData);
        int objectCount = 0;
        try (MappingIterator<Dto> it = objectMapper.readerFor(Dto.class).readValues(dataFile))
        {
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
