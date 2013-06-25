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
import org.apache.tapestry.finder.entities.SourceType;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * Service for all {@link SourceType} related functionality.
 */

public class SourceTypeServiceImpl2 implements SourceTypeService
{
    private static final long serialVersionUID = -6369368152854509160L;
    @SuppressWarnings("rawtypes")
    @Inject
    private CayenneCrudServiceDAO crudService;
    private final static Class<SourceType> clazz = SourceType.class;

    private List<SourceType> sourceTypes;

    /**
     * Create a new {@link SourceType} instance.
     * 
     * @return Instance of SourceType with only ID populated and attached to a
     *         valid {@link ObjectContext}
     */
    @SuppressWarnings("unchecked")
    @Override
    public SourceType create()
    {
	SourceType sourceType = (SourceType) crudService.create(clazz);
	updateSourceTypeCache();
	return sourceType;
    }

    @SuppressWarnings("unchecked")
    @Override
    public SourceType findById(Integer id)
    {
	return (SourceType) crudService.findById(clazz, id);
    }

    @Override
    public List<SourceType> findAll()
    {
	if (sourceTypes == null)
	{
	    updateSourceTypeCache();
	}
	return sourceTypes;
    }

    @SuppressWarnings("unchecked")
    private void updateSourceTypeCache()
    {
	sourceTypes = crudService.queryList(clazz, null, new Ordering(
		SourceType.SORT_BY_PROPERTY, SortOrder.ASCENDING));
    }

    @SuppressWarnings("unchecked")
    public SourceType save(SourceType et)
    {
	SourceType license = (SourceType) crudService.save(et);
	this.updateSourceTypeCache();
	return license;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void delete(SourceType et)
    {
	crudService.delete(et);
	this.updateSourceTypeCache();
    }

}