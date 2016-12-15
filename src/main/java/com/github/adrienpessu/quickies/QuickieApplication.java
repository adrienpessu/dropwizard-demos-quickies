package com.github.adrienpessu.quickies;

import com.github.adrienpessu.quickies.configuration.QuickieConfiguration;
import com.github.adrienpessu.quickies.resources.QuickieResource;
import com.github.adrienpessu.quickies.services.QuickieService;
import com.mongodb.MongoClient;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class QuickieApplication extends Application<QuickieConfiguration> {

    @Override
    public void initialize(final Bootstrap<QuickieConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );
    }

    @Override
    public void run(QuickieConfiguration quickieConfiguration, Environment environment) throws Exception {

        final Morphia morphia = new Morphia();
        morphia.mapPackage("com.github.adrienpessu.quickies.models");
        morphia.getMapper().getOptions().setStoreEmpties(true);

        final Datastore datastore = morphia.createDatastore(
            new MongoClient(quickieConfiguration.getMongoHost(), quickieConfiguration.getMongoPortAsInt()), quickieConfiguration.getMongoDB());
        datastore.ensureIndexes();

        QuickieService quickieService = new QuickieService(quickieConfiguration, datastore);

        environment.jersey().register(new QuickieResource(quickieService));


        FilterRegistration.Dynamic corsFilter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        corsFilter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,OPTIONS");
        corsFilter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        corsFilter.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
        corsFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }

    public static void main(String[] args) throws Exception {
        new QuickieApplication().run(args);
    }
}
