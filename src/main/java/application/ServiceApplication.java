package application;

import configuration.AppConfiguration;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resources.DefaultAppResource;
import resources.DefaultEmailResource;
import resources.DefaultSettingsResource;

public class ServiceApplication extends Application<AppConfiguration> {

    public static void main(String[] args) throws Exception {
        new ServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "Services";
    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
    }

    @Override
    public void run(AppConfiguration configuration, Environment environment) {
        final DefaultAppResource resource = new DefaultAppResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );

        environment.jersey().register(resource);
        environment.jersey().register(new DefaultEmailResource());
        environment.jersey().register(new DefaultSettingsResource());
    }
}
