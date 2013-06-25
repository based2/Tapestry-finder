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
import org.apache.tapestry.finder.entities.License;
import org.apache.tapestry.finder.entities.TapestryVersion;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * Service for creating, managing and searching for {@link TapestryVersion}
 * entities. See also {@link GenericService}
 */

public class TapestryVersionServiceImpl2 implements TapestryVersionService
{
    private static final long serialVersionUID = 849691868904672441L;
    
    @SuppressWarnings("rawtypes")
    @Inject
    private CayenneCrudServiceDAO crudService;
    private final static Class<TapestryVersion> clazz = TapestryVersion.class;
    
    private List<TapestryVersion> tapestryVersions;
    
    /**
     * Create a new {@link License} instance.
     * 
     * @return Instance of License with only ID populated and attached to a
     *         valid {@link ObjectContext}
     */
    @SuppressWarnings("unchecked")
    @Override
    public TapestryVersion create()
    {
	TapestryVersion tapestryVersion = (TapestryVersion) crudService.create(clazz);
	this.updateTapestryVersionsCache();
	return tapestryVersion;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public TapestryVersion findById(Integer id)
    {
	return (TapestryVersion) crudService.findById(clazz, id);
    }
    
    @Override
    public List<TapestryVersion> findAll()
    {
	if (tapestryVersions==null)
	{
	    this.updateTapestryVersionsCache();
	}
	return tapestryVersions;
    }
    
    @SuppressWarnings("unchecked")
    private void updateTapestryVersionsCache()
    {
	tapestryVersions = crudService.queryList(clazz, null, 
		new Ordering(TapestryVersion.SORT_BY_PROPERTY, SortOrder.ASCENDING));
    }

    @SuppressWarnings("unchecked")
    @Override
    public TapestryVersion save(TapestryVersion et)
    {
	TapestryVersion tapestryVersion = (TapestryVersion) crudService.save(et);
	this.updateTapestryVersionsCache();
	return tapestryVersion;
    }

    @SuppressWarnings("unchecked")
    public void delete(TapestryVersion et)
    {
	crudService.delete(et);
	this.updateTapestryVersionsCache();
    }
}