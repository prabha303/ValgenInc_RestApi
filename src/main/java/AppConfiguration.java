import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.DatabaseConfiguration;
import org.eclipse.jetty.util.annotation.Name;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by prasi on 7/2/17.
 */
public class AppConfiguration extends Configuration
{
    @Valid
    @NotNull
    @JsonProperty
    private String swaggerBasePath;
    public String getSwaggerBasePath(){ return swaggerBasePath; }

    @NotNull
    @JsonProperty("oho")
    private DataSourceFactory ohoDataBase;


    public DataSourceFactory getOhoDataBase() {
        return ohoDataBase;
    }



}


