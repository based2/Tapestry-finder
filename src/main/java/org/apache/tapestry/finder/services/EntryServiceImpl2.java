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

import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.exp.ExpressionFactory;
import org.apache.cayenne.query.Ordering;
import org.apache.cayenne.query.SortOrder;
import org.apache.tapestry.finder.entities.ComponentEntry;
import org.apache.tapestry.finder.entities.EntryType;
import org.apache.tapestry.finder.entities.SourceType;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry.finder.dal.CayenneCrudServiceDAO;

/**
 * Service for all {@link ComponentEntry} related functionality.
 */
public class EntryServiceImpl2 implements EntryService
{
    private static final long serialVersionUID = -8138215930262318174L;
    
    @SuppressWarnings("rawtypes")
    @Inject
    private CayenneCrudServiceDAO crudService;
    private final static Class<ComponentEntry> clazz = ComponentEntry.class;
    
    @SuppressWarnings("unchecked")
    @Override
    public ComponentEntry create()
    {
	return (ComponentEntry) crudService.create(clazz);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ComponentEntry> findAll()
    {
	return crudService.findAllOrdered(clazz);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ComponentEntry> findByType(EntryType entryType,
	    List<SourceType> sourceTypes)
    {
	Expression exp;
	if (entryType == null)
	{
	    exp = null;
	} else
	{
	    // filter out entries of the same type as the given entry
	    exp = ExpressionFactory.matchExp(
		    ComponentEntry.ENTRY_TYPE_PROPERTY, entryType);
	}
	// TODO: add expression for matching any of the sourceType values

	return crudService.queryList(clazz, exp,
		new Ordering(ComponentEntry.NAME_PROPERTY,
			SortOrder.ASCENDING_INSENSITIVE));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ComponentEntry> findParentCandidates(ComponentEntry entry)
    {
	
	// TODO: need complex expression saying "all entries whose EntryType
	// relationship refers to an EntryType with "isContainer" true

	Expression exp;
	if (entry == null)
	{
	    exp = null;
	} else
	{
	    // filter out entries of the same type as the given entry
	    exp = ExpressionFactory.noMatchExp(
		    ComponentEntry.ENTRY_TYPE_PROPERTY, entry.getEntryType());
	}
	
	return crudService.queryList(clazz, exp,
		new Ordering(ComponentEntry.NAME_PROPERTY,
			SortOrder.ASCENDING_INSENSITIVE));
    }

    @SuppressWarnings("unchecked")
    @Override
    public ComponentEntry findById(Integer id)
    {
	return (ComponentEntry) crudService.findById(clazz, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public ComponentEntry save(ComponentEntry ce)
    {
	return (ComponentEntry) crudService.save(ce);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void delete(ComponentEntry ce)
    {
	crudService.delete(ce);
    }

}