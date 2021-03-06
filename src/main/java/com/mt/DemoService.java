package com.mt;

/**
 * Created by bing.du on 2/17/14.
 */


import com.mt.Core.City;
import com.mt.Core.Store;
import com.mt.Core.product;
import com.mt.DB.IStoreJDBIDao;
//import com.mt.DB.StoreDao;
import com.mt.DB.ProductDao;
import com.mt.DB.StoreDao;
import com.mt.Resource.ActionResource;
import com.mt.Resource.DemoResource;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.db.DatabaseConfiguration;
//import com.yammer.dropwizard.hibernate.HibernateBundle;
import com.yammer.dropwizard.hibernate.HibernateBundle;
import com.yammer.dropwizard.jdbi.DBIFactory;
import com.yammer.dropwizard.views.ViewBundle;
import org.skife.jdbi.v2.DBI;

public class DemoService extends Service<DemoConfiguration> {
    public static void main(String[] args) throws Exception {
        new DemoService().run(args);
    }

    private final HibernateBundle<DemoConfiguration> hibernate = new HibernateBundle<DemoConfiguration>(Store.class,City.class) {
        @Override
        public DatabaseConfiguration getDatabaseConfiguration(DemoConfiguration configuration) {

            return configuration.getDatabaseConfiguration();
        }
    };


    @Override
    public void initialize(Bootstrap<DemoConfiguration> bootstrap) {
        bootstrap.setName("hello-world");
        bootstrap.addBundle(new ViewBundle());
        bootstrap.addBundle(new AssetsBundle("/assets/js", "/js"));
        bootstrap.addBundle(new AssetsBundle("/assets/css", "/css"));
        bootstrap.addBundle(new AssetsBundle("/assets/img", "/img"));
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(DemoConfiguration configuration,
                    Environment environment) throws ClassNotFoundException{


        //final String template = configuration.getTemplate();
        //final String defaultName = configuration.getDefaultName();
        //environment.addResource(new HelloWorldResource(template, defaultName));
        ProductDao hibernateProductDao= new ProductDao(hibernate.getSessionFactory());
        StoreDao hibernateStoreDao = new StoreDao(hibernate.getSessionFactory());


        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDatabaseConfiguration(), "mysql");
        IStoreJDBIDao jdbiDao = jdbi.onDemand(IStoreJDBIDao.class);
        environment.addResource(new ActionResource(hibernateStoreDao, null));
        environment.addResource(new DemoResource(hibernateStoreDao,hibernateProductDao,null));

    }

}
