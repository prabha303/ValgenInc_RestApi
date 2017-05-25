import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.wordnik.swagger.config.ConfigFactory;
import com.wordnik.swagger.config.ScannerFactory;
import com.wordnik.swagger.config.SwaggerConfig;
import com.wordnik.swagger.jaxrs.config.DefaultJaxrsScanner;
import com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider;
import com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON;
import com.wordnik.swagger.jaxrs.listing.ResourceListingProvider;
import com.wordnik.swagger.jaxrs.reader.DefaultJaxrsApiReader;
import com.wordnik.swagger.reader.ClassReaders;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import ohoDAO.*;
import io.dropwizard.Application;
import io.dropwizard.hibernate.ScanningHibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import ohoDAO.entities.DEFECTSJDAO;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.skife.jdbi.v2.DBI;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

/**
 * Created by prasi on 7/2/17.
 */
public class App extends Application<AppConfiguration>
{
    public static void main(String[] args)
    {
        try
        {
            new App().run("server", "config.yml");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private final ScanningHibernateBundle<AppConfiguration> ohoHibernate = new ScanningHibernateBundle<AppConfiguration>("ohoDAO.entities") {
        @Override
        protected void configure(Configuration configuration) {
            // Register package so global filters in package-info.java get seen.
            configuration.addPackage("ohoDAO.entities");
            super.configure(configuration);
        }

        @Override
        public PooledDataSourceFactory getDataSourceFactory(AppConfiguration config) {
            return config.getOhoDataBase();
        }

        @Override
        protected String name() {
            return "hibernate";
        }

        @Override
        public boolean isLazyLoadingEnabled() {
            return true;
        }

        @Override
        protected Hibernate5Module createHibernate5Module() {
            return super.createHibernate5Module();
        }
    };



    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        bootstrap.addBundle(ohoHibernate);
        bootstrap.addBundle(new AssetsBundle("/assets", "/docs", "index.html"));
    }

    public void run(AppConfiguration appConfiguration, Environment environment) throws Exception
    {
        enableCORS(environment);

        //Two session factory
        SessionFactory ohoSessionFactory = ohoHibernate.getSessionFactory();  // my sql server session
        //TruckerDAO truckerDAO = new TruckerDAO(ohoSessionFactory);
        initSwagger(appConfiguration, environment);
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, appConfiguration.getOhoDataBase(), "h2");

        final DEFECTSJDAO defectsjdao = jdbi.onDemand(DEFECTSJDAO.class);
        environment.jersey().register(new Defects(defectsjdao));
    }



    private void enableCORS(Environment environment)
    {
        FilterRegistration.Dynamic filter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,OPTIONS");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        filter.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }



    private void initSwagger(AppConfiguration configuration, Environment environment) {
        // Swagger Resource
        environment.jersey().register(new ApiListingResourceJSON());

        // Swagger providers
        environment.jersey().register(new ApiDeclarationProvider());
        environment.jersey().register(new ResourceListingProvider());

        // Swagger Scanner, which finds all the resources for @Api Annotations
        ScannerFactory.setScanner(new DefaultJaxrsScanner());

        // Add the reader, which scans the resources and extracts the resource information
        ClassReaders.setReader(new DefaultJaxrsApiReader());

        // required CORS support
        FilterRegistration.Dynamic filter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        filter.setInitParameter("allowedOrigins", "*"); // allowed origins comma separated
        filter.setInitParameter("allowedHeaders", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
        filter.setInitParameter("allowedMethods", "GET,PUT,POST,DELETE,OPTIONS,HEAD");
        filter.setInitParameter("preflightMaxAge", "5184000"); // 2 months
        filter.setInitParameter("allowCredentials", "true");

        // Set the swagger config options
        SwaggerConfig config = ConfigFactory.config();
        config.setApiVersion("1.0.1");
        config.setBasePath(configuration.getSwaggerBasePath());
    }
}
