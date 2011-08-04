package org.apache.tapestry.finder.dal;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.PersistentObject;
import org.apache.cayenne.access.DataContext;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.exp.ExpressionFactory;
import org.apache.cayenne.map.ObjEntity;
import org.apache.cayenne.query.Ordering;
import org.apache.cayenne.query.SelectQuery;
import org.apache.cayenne.query.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

/**
 * Abstract class that implements the {@link GenericService} interface, using
 * Apache Cayenne. Services can extend this class to get Cayenne-based
 * implementations of the most commonly-needed database access methods.
 * 
 * @param <T>
 * @param <PK>
 */
public class CayenneCrudServiceDAOImpl<T extends CayenneDataObject, PK extends Serializable>
implements CayenneCrudServiceDAO
{
    Logger log = LoggerFactory.getLogger(CayenneCrudServiceDAOImpl.class);

    /**
     * When this is not null, queries should be cached within the named region.
     * Subclasses may set the region name.
     */
   // protected String queryCacheRegion;

   // private final Class<T> persistentClass;

    private ObjectContext context;
    
    public ObjectContext getObjectContext()
    {
	if (context==null)
	{
	    context = DataContext.getThreadObjectContext();
	}
	return context;
    }
    
    @SuppressWarnings({ "unchecked", "hiding" })
    public <T> List<T> queryList(SelectQuery query)
    {
	try
	{
	    return getObjectContext().performQuery(query);
	} catch (NullPointerException e)
	{
	    log.error("No context available", e);
	    return null;
	}
    }
    
    @SuppressWarnings({ "unchecked", "hiding" })
    @Override
    public <T> T queryUnique(SelectQuery query)
    {
	List<?> components = this.queryList(query);
	if (components.size() == 0)
	{
	    log.error("Not unique: count:" + components.size());
	    return null;
	}
	return (T) components.get(0);
    }
    
    @SuppressWarnings("hiding")
    public <T> SelectQuery createQuery(Class<T> t, Expression exp, Ordering order)
    {	
	SelectQuery query;
	if (exp != null)
	{
	    query = new SelectQuery(t, exp);
	} else { 
	    query = new SelectQuery(t);
	}
	if (order != null) {
	    query.addOrdering(order);
	}
	return query;
    }
    
    @SuppressWarnings("hiding")
    public <T> List<T> queryList(Class<T> t, Expression exp)
    {
	return queryList(createQuery(t, exp, null));
    }
    
    @SuppressWarnings("hiding")
    public <T> List<T> queryList(Class<T> t, Expression exp, Ordering order)
    {
	return queryList(createQuery(t, exp, order));
    }
    
    @SuppressWarnings("hiding")
    public <T> T queryUnique(Class<T> t, Expression exp)
    {
	return queryUnique(createQuery(t, exp, null));
    }
    
    @SuppressWarnings("hiding")
    public <T> T queryUnique(Class<T> t, Expression exp, Ordering order)
    {
	return queryUnique(createQuery(t, exp, order));
    }
    
    /**
     * Flush the Cayenne session to the database.
     * 
     * @throws Exception
     */
    public void flush()
    {
	flush();
    }
	
    @SuppressWarnings({ "unchecked", "hiding" })
    public <T> List<T> findAll(T t)
    {
	SelectQuery query = new SelectQuery((ObjEntity) t);
	return (List<T>) this.queryList(query);
    }
    
    @SuppressWarnings({ "hiding" })
    public <T> List<T> findAllOrdered(Class<T> t)
    {
	return (List<T>) this.queryList(t, null, 
		new Ordering(CayenneCrudServiceDAOImpl.NAME,
			SortOrder.ASCENDING_INSENSITIVE));
    }
    
    @SuppressWarnings({ "unchecked", "hiding" })
    public <T> List<T> findAll(Class<T> t, Ordering order)
    {
	SelectQuery query = new SelectQuery(t);
	query.addOrdering(order);
	return (List<T>) this.queryList(query);
    }

    @SuppressWarnings("hiding")
	public <T, PK> T findById(Class<T> t, PK id)
    {
	return (T) this.queryUnique(t, 
		ExpressionFactory.inExp(CayenneCrudServiceDAO.ID, id));
    }
	
    /**
     * Performs a commit to the database of an instance of
     * {@link CayenneDataObject}.
     * <p>
     * IMPORTANT NOTE: Cayenne commits all changes in the context, not just the
     * changes in this one entity. Most of the time this is what you want, but
     * if not, then you need to ensure that what you want to commit is in a
     * different context from what you don't.
     * 
     * @param entity
     */
    @SuppressWarnings("hiding")
    public synchronized <T> T save(T t)
    {
	return this.update(t);
    }

    @SuppressWarnings("hiding")
    public synchronized <T> void delete(T t)
    {
	Assert.assertNotNull(t, "No Entity Specified");

	ObjectContext context = this.getObjectContext();
	context.deleteObject(t);
	context.commitChanges();
    }
    
    //////////////////////////////////////////////////////////////////////////////

    @SuppressWarnings({ "hiding" })
    public synchronized <T> T create(Class<T> t)
    {
	try
	{
	    return this.getObjectContext().newObject(t);
	} catch (NullPointerException e)
	{
	    log.error("No context available", e);
	    return null;
	}
    }

    public synchronized T save(T t)
    {
	ObjectContext context;
	if (((PersistentObject) t).getObjectContext() == null)
	{
	    log.debug("In save(), new entity=" + t + "\n");
	    context = getObjectContext();
	    context.registerNewObject(t);
	} else
	{
	    log.debug("In save(), existing entity=" + t + "\n");
	    context = ((PersistentObject) t).getObjectContext();
	}
	context.commitChanges();
	return t;
    }
    
    @SuppressWarnings("hiding")
    public synchronized <T> T update(T t)
    {
	log.debug("In save(), existing entity=" + t + "\n");
	ObjectContext context = ((PersistentObject) t).getObjectContext();
	context.commitChanges();
	return t;
    }

    @SuppressWarnings("hiding")
    @Override
    public <T, PK extends Serializable> void delete(Class<T> t, PK id)
    {
	Assert.assertNotNull(t, "No Entity Specified");

	ObjectContext context = this.getObjectContext();
	context.deleteObject(t);
	context.commitChanges();
    }

    @SuppressWarnings("hiding")
    @Override
    public <T, PK extends Serializable> T find(Class<T> type, PK id)
    {
	// TODO Auto-generated method stub
	return null;
    }

    @SuppressWarnings("hiding")
    @Override
    public <T> List<T> findWithNamedQuery(String queryName)
    {
	// TODO Auto-generated method stub
	return null;
    }

    @SuppressWarnings("hiding")
    @Override
    public <T> List<T> findWithNamedQuery(String queryName,
	    Map<String, Object> params)
    {
	// TODO Auto-generated method stub
	return null;
    }

    @SuppressWarnings("hiding")
    @Override
    public <T> T findUniqueWithNamedQuery(String queryName)
    {
	// TODO Auto-generated method stub
	return null;
    }

    @SuppressWarnings("hiding")
    @Override
    public <T> T findUniqueWithNamedQuery(String queryName,
	    Map<String, Object> params)
    {
	// TODO Auto-generated method stub
	return null;
    }

}