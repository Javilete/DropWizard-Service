package resources;

import com.mongodb.*;
import core.defaults.DefaultSettings;
import core.models.Settings;
import dao.MongoConnection;

import javax.ws.rs.Path;

@Path("/myApp/settings")
public class DefaultSettingsResource extends SettingsResource {

    private static final String DATABASE = "test";
    private static final String EMAIL_COLLECTION = "settings";
    private static final String EMAIL = "email";

    static MongoClient mc;
    static DB database;
    static DBCollection collection;


    static{
        mc = MongoConnection.getInstance();
        database = mc.getDB(DATABASE);
        collection = database.getCollection(EMAIL_COLLECTION);
    }

    @Override
    public Settings getSettingsImpl(String userId) {

        Settings settings = null;

        BasicDBObject obj = (BasicDBObject) collection.findOne(new BasicDBObject(EMAIL, userId));

        if(obj != null) {
            settings = new DefaultSettings();
            settings.setName(obj.getString("name"));
            settings.setAge(obj.getInt("age"));
            settings.setEmail(obj.getString("email"));
        }

        return settings;
    }
}
