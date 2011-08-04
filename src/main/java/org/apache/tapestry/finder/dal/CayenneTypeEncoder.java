package org.apache.tapestry.finder.dal;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.tapestry.finder.entities.EntryType;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.services.ValueEncoderFactory;


/**
 * Cayenne CrudService
 * 
 * @author basile.chandesris
 * @param <T>, type injected service 
 */
public class CayenneTypeEncoder 
	implements ValueEncoder<EntryType>,
	ValueEncoderFactory<EntryType>
{

    /**
     * Return the given object's ID
     * @param value
     * @return id
     */
    @Override
    public String toClient(EntryType value)
    {
	if (value == null)
	{
	    return null;
	}
	return String.valueOf(value.getId());
    }
    
    public EntryType toValue(String id, String n)
    {
	if (id == null)
	{
	    return null;
	}
	// find the componentEntry object of the given ID in the database
	/*try
	{
	    return entryTypeService.findById(Integer.parseInt(id));
	} catch (NumberFormatException e)
	{
	    throw new RuntimeException("ID " + id + " is not a number", e);
	}*/
	return null;
    }

    // let this ValueEncoder also serve as a ValueEncoderFactory
    @Override
    public ValueEncoder<EntryType> create(Class<EntryType> type)
    {
	return this;
    }

    @Override
    public EntryType toValue(String arg0)
    {
	// TODO Auto-generated method stub
	return null;
    }
}
