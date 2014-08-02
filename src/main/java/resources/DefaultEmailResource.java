package resources;


import core.models.Email;

import javax.ws.rs.Path;
import java.util.List;

@Path("/myApp/email")
public final class DefaultEmailResource extends EmailResource {

    @Override
    public List<Email> getEmailsImpl(String s) {
        return null;
    }
}
