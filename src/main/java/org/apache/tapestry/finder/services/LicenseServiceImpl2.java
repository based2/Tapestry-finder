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
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * Service for creating, managing and searching for {@link License}
 * entities. See also {@link GenericService}
 */

public class LicenseServiceImpl2 implements LicenseService
{
    private static final long serialVersionUID = -4830536465229230036L;

    @SuppressWarnings("rawtypes")
    @Inject
    private CayenneCrudServiceDAO crudService;
    private final static Class<License> clazz = License.class;
    
    private List<License> licenses;
    
    /**
     * Create a new {@link License} instance.
     * 
     * @return Instance of License with only ID populated and attached to a
     *         valid {@link ObjectContext}
     */
    @SuppressWarnings("unchecked")
    @Override
    public License create()
    {
	return (License) crudService.create(clazz);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public License findById(Integer id)
    {
	return (License) crudService.findById(clazz, id);
    }
    
    @Override
    public List<License> findAll()
    {
	if (licenses==null)
	{
	    this.updateLicensesCache();
	}
	return licenses;
    }
    
    @SuppressWarnings("unchecked")
    private void updateLicensesCache()
    {
	licenses = crudService.queryList(clazz, null, 
		new Ordering(License.NAME_PROPERTY, SortOrder.DESCENDING_INSENSITIVE));
    }

    @SuppressWarnings("unchecked")
    @Override
    public License save(License et)
    {
	License license = (License) crudService.save(et);
	this.updateLicensesCache();
	return license;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void delete(License et)
    {
	crudService.delete(et);
	this.updateLicensesCache();
    }

}