package main.services.mongodb;
import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import main.constants.constants;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;

import static main.constants.constants.DATABASE_NAME;

public class mongodb {


    private static MongoClient mongoClient;
    private static MongoDatabase database;

    static {
        mongoClient = MongoClients.create(constants.CONNECTION_STRING);
        database = mongoClient.getDatabase(DATABASE_NAME);
    }

    public mongodb() {}

    public static MongoCollection<Document> getCollection(String collectionName) {
        return database.getCollection(collectionName);
    }

    public static <T> T toObject(Document doc, Class<T> clazz) {
        Gson gson = new Gson();
        String json = doc.toJson();
        return gson.fromJson(json, clazz);
    }

    public static Document toDocument(Object obj) {
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        return Document.parse(json);
    }

    public static <T> List<T> toList(MongoCollection<Document> collection, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        for (Document doc : collection.find()) {
            list.add(toObject(doc, clazz));
        }
        return list;
    }

    public static <T> List<T> toList(List<Document> documents, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        for (Document doc : documents) {
            list.add(toObject(doc, clazz));
        }
        return list;
    }

    public static <T> List<T> toList(MongoCollection<Document> collection, Bson filter, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        for (Document doc : collection.find(filter)) {
            list.add(toObject(doc, clazz));
        }
        return list;
    }

    public static <T> List<T> toList(MongoCollection<Document> collection, String field, String value, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        for (Document doc : collection.find(Filters.eq(field, value))) {
            list.add(toObject(doc, clazz));
        }
        return list;
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

    public static void updateOne(String collectionName, Bson filter, Document update) {
        getCollection(collectionName).updateOne(filter, update);
    }

    public static void updateMany(String collectionName, Document filter, Document update) {
        getCollection(collectionName).updateMany(filter, update);
    }

    public static void updateMany(String collectionName, Bson filter, Document update) {
        getCollection(collectionName).updateMany(filter, update);
    }

    public static void deleteOne(String collectionName, Document filter) {
        getCollection(collectionName).deleteOne(filter);
    }

    public static void deleteOne(String collectionName, Bson filter) {
        getCollection(collectionName).deleteOne(filter);
    }

    public static void deleteMany(String collectionName, Document filter) {
        getCollection(collectionName).deleteMany(filter);
    }

    public static void deleteMany(String collectionName, Bson filter) {
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
