package resources;

import com.mongodb.*;
import core.defaults.DefaultEmail;
import core.models.Email;
import dao.MongoConnection;

import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path("/myApp/email")
public final class DefaultEmailResource extends EmailResource {
    //Database
    private static final String DATABASE = "test";
    private static final String EMAIL_COLLECTION = "email";
    private static final String FROM = "from";
    private static final String TO = "to";
    private static final String CONTENT = "content";
    private static final String SUBJECT = "subject";
    private static final String DATE = "date";

    static MongoClient mc;
    static DB database;
    static DBCollection collection;


    static{
        mc = MongoConnection.getInstance();
        database = mc.getDB(DATABASE);
        collection = database.getCollection(EMAIL_COLLECTION);
    }

    @Override
    public List<Email> getEmailsImpl(String s) {
//        MongoClient mc = MongoConnection.getInstance();
//        DB database = mc.getDB(DATABASE);
//        DBCollection collection = database.getCollection(EMAIL_COLLECTION);

        List<Email> result;

        DBCursor cursor = collection.find(new BasicDBObject(TO,s));
        try {
            if(cursor != null){
                result = new ArrayList<Email>();
                while (cursor.hasNext()) {
                    BasicDBObject obj = (BasicDBObject) cursor.next();
                    Email email = new DefaultEmail();
                    email.setContent((String) obj.getString(CONTENT));
                    email.setFrom((String) obj.getString(FROM));
                    email.setDate((Date) obj.getDate(DATE));
                    email.setSubject((String) obj.getString(SUBJECT));
                    email.setTo((String) obj.getString(TO));
                    result.add(email);
                }
                return result;
            }
        } finally {
            cursor.close();
        }

    return new ArrayList<Email>();
    }

    @Override
    public void sendEmailImpl(Email email) {
        BasicDBObject obj;

        if (email != null){
            obj = new BasicDBObject();
            obj.append(FROM, email.getFrom());
            obj.append(TO, email.getTo());
            obj.append(CONTENT, email.getContent());
            obj.append(SUBJECT, email.getSubject());
            obj.append(DATE, email.getDate());
            collection.insert(obj);
        }
    }
}
