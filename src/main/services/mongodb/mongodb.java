package main.services.mongodb;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class mongodb {
    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "course_registration";

    private static MongoClient mongoClient;
    private static MongoDatabase database;

    static {
        mongoClient = MongoClients.create(CONNECTION_STRING);
        database = mongoClient.getDatabase(DATABASE_NAME);
    }

    public mongodb() {}

    public static MongoCollection<Document> getCollection(String collectionName) {
        return database.getCollection(collectionName);
    }

    public static void insertOne(String collectionName, Document document) {
        getCollection(collectionName).insertOne(document);
    }

    public static void insertOne(String collectionName, String json) {
        Document document = Document.parse(json);
        getCollection(collectionName).insertOne(document);
    }

    public static void insertMany(String collectionName, List<Document> documents) {
        getCollection(collectionName).insertMany(documents);
    }

    public static void updateOne(String collectionName, Document filter, Document update) {
        getCollection(collectionName).updateOne(filter, update);
    }

    public static void updateMany(String collectionName, Document filter, Document update) {
        getCollection(collectionName).updateMany(filter, update);
    }

    public static void deleteOne(String collectionName, Document filter) {
        getCollection(collectionName).deleteOne(filter);
    }

    public static void deleteMany(String collectionName, Document filter) {
        getCollection(collectionName).deleteMany(filter);
    }

    public static long countDocuments(String collectionName, Document filter) {
        return getCollection(collectionName).countDocuments(filter);
    }

    public static List<Document> find(String collectionName, Document filter) {
        return getCollection(collectionName).find(filter).into(new ArrayList<>());
    }

    public static void deleteAll(String collectionName) {
        getCollection(collectionName).deleteMany(new Document());
    }
}
