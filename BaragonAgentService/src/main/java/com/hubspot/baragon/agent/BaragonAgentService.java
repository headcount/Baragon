package com.hubspot.baragon.agent;

import com.google.inject.Stage;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class BaragonAgentService extends Application<BaragonAgentConfiguration> {

  @Override
  public void initialize(Bootstrap<BaragonAgentConfiguration> bootstrap) {
    GuiceBundle<BaragonAgentConfiguration> guiceBundle = GuiceBundle.<BaragonAgentConfiguration>newBuilder()
        .addModule(new BaragonAgentServiceModule())
        .enableAutoConfig(getClass().getPackage().getName())
        .setConfigClass(BaragonAgentConfiguration.class)
        .build(Stage.DEVELOPMENT);
    bootstrap.addBundle(guiceBundle);
  }

  @Override
  public void run(BaragonAgentConfiguration configuration, Environment environment) throws Exception {}

  public static void main(String[] args) throws Exception {
    new BaragonAgentService().run(args);
  }

}
