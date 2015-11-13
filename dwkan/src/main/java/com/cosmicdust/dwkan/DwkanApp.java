package com.cosmicdust.dwkan;

import com.cosmicdust.dwkan.config.DwkanConfig;
import com.cosmicdust.dwkan.health.TemplateHealthCheck;
import com.cosmicdust.dwkan.resources.HelloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by nshah on 10/22/2015.
 */
public class DwkanApp extends Application<DwkanConfig> {
    public static void main(String[] args) throws Exception {
        new DwkanApp().run(args);
    }

    @Override
    public String getName() {
        return "DropWizard Kan";
    }

    @Override
    public void initialize(Bootstrap<DwkanConfig> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(DwkanConfig configuration,
                    Environment environment) {
        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        environment.jersey().register(resource);

        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);


    }


}
