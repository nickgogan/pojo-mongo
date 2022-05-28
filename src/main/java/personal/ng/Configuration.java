package personal.ng;

public class Configuration {
    public static String connectionString = "mongodb+srv://service:service@fa.znnva.mongodb.net/FA?retryWrites=true";
    public static String db = "FA";
    public static String coll = "test";
    public static String[] jsonDataFilePaths = new String[]{ "src/main/resources/sampleData.json"};
    public static String[] valueFilters = new String[] {"-", "--"};
}
