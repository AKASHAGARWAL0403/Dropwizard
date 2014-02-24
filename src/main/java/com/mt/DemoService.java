package com.mt;

/**
 * Created by bing.du on 2/17/14.
 */


import com.mt.Resource.DemoResource;
import com.mt.Resource.HelloWorldResource;
import com.mt.Resource.StatisticResource;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.views.ViewBundle;

public class DemoService extends Service<DemoConfiguration> {
    public static void main(String[] args) throws Exception {
        new DemoService().run(args);
    }

    @Override
    public void initialize(Bootstrap<DemoConfiguration> bootstrap) {
        bootstrap.setName("hello-world");
        bootstrap.addBundle(new ViewBundle());
        bootstrap.addBundle(new AssetsBundle("/assets/js", "/js"));
        bootstrap.addBundle(new AssetsBundle("/assets/css", "/css"));
        bootstrap.addBundle(new AssetsBundle("/assets/img", "/img"));

    }

    @Override
    public void run(DemoConfiguration configuration,
                    Environment environment) {
        final String template = configuration.getTemplate();
        final String defaultName = configuration.getDefaultName();
        environment.addResource(new HelloWorldResource(template, defaultName));
        environment.addResource(new DemoResource());
        environment.addResource(new StatisticResource());
    }

}
