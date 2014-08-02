package resources;

import com.google.common.base.Optional;
import core.Saying;

import javax.ws.rs.Path;
import java.util.concurrent.atomic.AtomicLong;

@Path("/app-service")
public final class DefaultAppResource extends AppResource {

    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public DefaultAppResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @Override
    public Saying sayHelloImpl(Optional<String> stringOptional) {
        final String value = String.format(template, stringOptional.or(defaultName));
        return new Saying(counter.incrementAndGet(), value);
    }
}

