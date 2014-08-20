package dao;

import com.mongodb.MongoClient;

import java.net.UnknownHostException;

public class MongoConnection {
    public static MongoClient mongoClient;

    public static MongoClient getInstance() {
        if(mongoClient == null){
            try {
                return new MongoClient( "localhost" , 27017 );
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        return mongoClient;
    }
}
