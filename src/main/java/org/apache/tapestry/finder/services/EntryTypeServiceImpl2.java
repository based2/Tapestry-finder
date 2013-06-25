/* Copyright 2011 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.tapestry.finder.services;

import java.util.List;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.query.Ordering;
import org.apache.cayenne.query.SortOrder;
import org.apache.tapestry.finder.dal.CayenneCrudServiceDAO;
import org.apache.tapestry.finder.entities.EntryType;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * Service for all {@link EntryType} related functionality.
 */

public class EntryTypeServiceImpl2 implements EntryTypeService
{
    private static final long serialVersionUID = -7225741842868434242L;
    @SuppressWarnings("rawtypes")
    @Inject
    private CayenneCrudServiceDAO crudService;
    private final static Class<EntryType> clazz = EntryType.class;
    private List<EntryType> entryTypes;
    
    /**
     * Create a new {@link EntryType} instance.
     * 
     * @return Instance of EntryType with only ID populated and attached to a
     *         valid {@link ObjectContext}
     */
    @SuppressWarnings("unchecked")
    @Override
    public EntryType create()
    {
	EntryType entryType = (EntryType) crudService.create(clazz);
	updateEntryTypesCache();
	return entryType;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public EntryType findById(Integer id)
    {
	return (EntryType) crudService.findById(clazz, id);
    }
    
    @Override
    public List<EntryType> findAll()
    {
	if (entryTypes==null)
	{
	    this.updateEntryTypesCache();
	}
	return entryTypes;
    }
    
    @SuppressWarnings("unchecked")
    private void updateEntryTypesCache()
    {
	entryTypes = crudService.queryList(clazz, null, 
		new Ordering(EntryType.SORT_BY_PROPERTY,
		SortOrder.ASCENDING));
    }

    @SuppressWarnings("unchecked")
    @Override
    public EntryType save(EntryType et)
    {
	EntryType entryType = (EntryType) crudService.save(et);
	updateEntryTypesCache();
	return entryType;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void delete(EntryType et)
    {
	crudService.delete(et);
	updateEntryTypesCache();
    }

}