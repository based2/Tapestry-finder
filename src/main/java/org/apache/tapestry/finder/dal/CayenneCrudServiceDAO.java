package org.apache.tapestry.finder.dal;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.query.Ordering;
import org.apache.cayenne.query.SelectQuery;

/**
 * CrudServiceDAO interface.
 * 
 * @author karesti, basile.chandesris
 */
public interface CayenneCrudServiceDAO //<T extends CayenneDataObject, PK extends Serializable> 
{
    public static final String ID = "id";
    public static final String NAME = "name";
    
    /**
     * Creates a new object for the given type. After a call to this method the entity will be
     * persisted into database and then refreshed. Also current persistent Session will be flushed.
     * 
     * @param <T>
     * @param t
     * @return persisted Object
     */
    <T> T create(Class<T> t);

    /**
     * Updates the given object
     * 
     * @param <T>
     * @param t
     * @return persisted object
     */
    <T> T update(T t);

    /**
     * Deletes the given object by id
     * 
     * @param <T>
     * @param <PK>
     * @param type, entity class type
     * @param id
     */
    <T, PK extends Serializable> void delete(Class<T> type, PK id);

    /**
     * Finds an object by id
     * 
     * @param <T>
     * @param <PK>
     * @param type
     * @param id
     * @return the object
     */
    <T, PK extends Serializable> T find(Class<T> type, PK id);

    /**
     * Finds a list of objects for the given query name
     * 
     * @param <T>
     * @param queryName
     * @return returns a list of objects
     */
    <T> List<T> findWithNamedQuery(String queryName);

    /**
     * Find a query with parameters
     * 
     * @param <T>
     * @param queryName
     * @param params
     * @return resulting list
     */
    <T> List<T> findWithNamedQuery(String queryName, Map<String, Object> params);

    /**
     * Returns one result, query without parameters
     * 
     * @param <T>
     * @param queryName
     * @return T object
     */
    <T> T findUniqueWithNamedQuery(String queryName);

    /**
     * Returns just one result with a named query and parameters
     * 
     * @param <T>
     * @param queryName
     * @param params
     * @return T object
     */
    <T> T findUniqueWithNamedQuery(String queryName, Map<String, Object> params);
    
    <T> List<T> queryList(SelectQuery query);
    <T> List<T> queryList(Class<T> t, Expression exp);
    <T> List<T> queryList(Class<T> t, Expression exp, Ordering order);
    
    <T> T queryUnique(SelectQuery query);
    <T> T queryUnique(Class<T> t, Expression exp);
    <T> T queryUnique(Class<T> t, Expression exp, Ordering order);
    
    ObjectContext getObjectContext();
    <T> SelectQuery createQuery(Class<T> t, Expression exp, Ordering order);
    
    <T> List<T> findAllOrdered(Class<T> t);
    <T> List<T> findAll(Class<T> t, Ordering order);
    <T, PK> T findById(Class<T> t, PK id);

    <T> void delete(T t);
    <T> T save(T t);
}
