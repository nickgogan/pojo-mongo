package personal.ng;

public class Configuration {
    public static String connectionString = "mongodb+srv://ng:ng@fa.znnva.mongodb.net/FA?retryWrites=true";
    public static String db = "FA";
    public static String coll = "test";
//    public static String inputData = "C:\\[00] Work\\Tech\\[02] Projects\\Java\\pojo-mongodb\\pojoMongo\\src\\main\\resources\\posts.json";
    public static String inputData = "C:\\[00] Work\\Tech\\[02] Projects\\Java\\pojo-mongodb\\pojoMongo\\src\\main\\resources\\post.json";
    public static String[] valueFilters = new String[] {"-", "--"};
    public static Integer writeBatchSize = 10_000;
}
