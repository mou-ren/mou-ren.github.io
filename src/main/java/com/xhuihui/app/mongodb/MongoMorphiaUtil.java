package com.xhuihui.app.mongodb;

import com.mongodb.MongoClient;
import org.bson.Document;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import java.util.List;

/**
 * Created by lihuiguang on 2017/8/18.
 */
public class MongoMorphiaUtil {


    public static void main(String[] args) {
        Morphia morphia = new Morphia();
        morphia.map(Person.class);
        Datastore db = morphia.createDatastore(MongoDBUtil.getMongoClient(), "db");
        Person p = new Person("xiaogang", 16);
        db.delete(db.createQuery(Person.class).field("age").equal(null));
        List<Person> peoples = db.createQuery(Person.class).asList();
        for (Person person : peoples){
            System.out.println(person);
        }
    }
}
