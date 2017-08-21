package com.xhuihui.app.mongodb;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Morphia;
public class MongoDBUtil {

    private static final MongoClient mongoClient;

    private static final String MONGO_HOST = "10.3.0.247";

    static{
        mongoClient = new MongoClient(MONGO_HOST);
    }

    public static MongoClient getMongoClient() {
        return mongoClient;
    }

    public static void main(String[] args) {
        MongoDatabase db = mongoClient.getDatabase("db");
        MongoCollection<Document> test = db.getCollection("test");
        BasicDBObject query = new BasicDBObject();
        query.put("name", "xhuihui");
        query.put("age", "18");
        FindIterable<Document> documents = test.find(query);
        for (Document d : documents){
            System.out.println(d.toJson());
        }
    }
}
