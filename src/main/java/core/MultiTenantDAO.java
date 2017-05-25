package core;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import javax.ws.rs.WebApplicationException;

import java.io.Serializable;
import java.util.List;

import static javax.ws.rs.core.Response.Status.FORBIDDEN;

/**
 * Created by prasi on 7/2/17.
 */
public abstract class MultiTenantDAO<T extends CoreModel, E extends CoreTenantData> extends AbstractDAO<T>
{
    protected E e = getE();
    public MultiTenantDAO(SessionFactory sessionFactory)
    {
        super(sessionFactory);
    }

    private void checkAuthorized() throws WebApplicationException
    {
        if(e.getThreadLocal() == null || e.getThreadLocal().get() == null)
        {
            throw new WebApplicationException(getOauthExceptionMessage(), FORBIDDEN);
        }
    }

    protected abstract String getTenantColumn();
    protected abstract String getTenantSchema();

    @Override
    protected Session currentSession()
    {
        return super.currentSession();
    }

    @Override
    protected Criteria criteria()
    {
        checkAuthorized();

        return super.criteria();
    }

    @Override
    protected Query namedQuery(String queryName) throws HibernateException
    {
        checkAuthorized();

        return super.namedQuery(queryName);
    }

    @Override
    public Class<T> getEntityClass()
    {
        checkAuthorized();

        return super.getEntityClass();
    }

    @Override
    protected T uniqueResult(Criteria criteria) throws HibernateException
    {
        checkAuthorized();

        return super.uniqueResult(criteria);
    }

    @Override
    protected T uniqueResult(Query query) throws HibernateException
    {
        checkAuthorized();

        return super.uniqueResult(query);
    }

    @Override
    protected List<T> list(Criteria criteria) throws HibernateException
    {
        checkAuthorized();

        return super.list(criteria);
    }

    @Override
    protected List<T> list(Query query) throws HibernateException
    {
        checkAuthorized();

        return super.list(query);
    }

    @Override
    protected T get(Serializable id)
    {
        checkAuthorized();

        return super.get(id);
    }



    protected T createData(T entity)
    {
        try
        {
            if (entity.getId() != null)
            {
                throw new WebApplicationException(getOauthExceptionMessage(), FORBIDDEN);
            }

            return super.persist(entity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    protected T getAuthenticatedData(Object value)
    {
        Criteria criteria = criteria();
        if(getTenantSchema() != null)
        {
            criteria.createAlias(getTenantSchema(), "t");
        }
        if(getTenantColumn() != null)
        {
            criteria.add(Restrictions.eq(getTenantColumn(), value));

            List<T> eList = list(criteria);

            if(eList != null && eList.size() > 0)
            {
                return eList.get(0);
            }
        }



        return null;
    }

    @Override
    protected T persist(T entity) throws HibernateException
    {
        checkAuthorized();

        return super.persist(entity);
    }

    @Override
    protected <T1> T1 initialize(T1 proxy) throws HibernateException
    {
        checkAuthorized();

        return super.initialize(proxy);
    }

    protected abstract String getOauthExceptionMessage();

    protected abstract E getE();
}
