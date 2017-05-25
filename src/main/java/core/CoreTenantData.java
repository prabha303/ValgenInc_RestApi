package core;

/**
 * Created by prasi on 7/2/17.
 */
public abstract class CoreTenantData<T>
{
    public abstract ThreadLocal<T> getThreadLocal();
}
