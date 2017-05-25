package core;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

/**
 * Created by prasi on 7/2/17.
 */
public abstract class OpenTenantDAO<T> extends AbstractDAO<T>
{
    public OpenTenantDAO(SessionFactory sessionFactory)
    {
        super(sessionFactory);
    }
}
