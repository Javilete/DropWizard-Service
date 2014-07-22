package application;

import configuration.AppConfiguration;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resources.AppResource;

public class ServiceApplication extends Application<AppConfiguration> {

    public static void main(String[] args) throws Exception {
        new ServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(AppConfiguration configuration, Environment environment) {
        final AppResource resource = new AppResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );

        environment.jersey().register(new AppResource(configuration.getTemplate(),configuration.getDefaultName()));
    }
}
