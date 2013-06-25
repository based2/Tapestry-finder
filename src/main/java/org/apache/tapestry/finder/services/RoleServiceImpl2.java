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

import org.apache.tapestry.finder.dal.CayenneCrudServiceDAO;
import org.apache.tapestry.finder.entities.Role;
import org.apache.tapestry5.ioc.annotations.Inject;

import org.apache.cayenne.ObjectContext;

/**
 * Service for all {@link Role} related functionality. See also
 * {@link GenericService}
 * 
 */
public class RoleServiceImpl2 implements RoleService
{

    @SuppressWarnings("rawtypes")
    @Inject
    private CayenneCrudServiceDAO crudService;
    private final static Class<Role> clazz = Role.class;

    private List<Role> roles;

    /**
     * Create a new {@link Role} instance.
     * 
     * @return Instance of Role with only ID populated and attached to a valid
     *         {@link ObjectContext}
     */
    //@SuppressWarnings("unchecked")
    //@Override
    /*
     * public Role create() { return (Role) crudService.create(clazz); }
     */
    
    /*
     * (non-Javadoc)
     * 
     * @see org.apache.tapestry.finder.services.RoleService#getAllRoles()
     */
    public List<Role> findAll()
    {
	if (roles == null)
	{
	    this.updateRolesCache();
	}
	return roles;
    }

    @SuppressWarnings("unchecked")
    private void updateRolesCache()
    {
	roles = crudService.findAllOrdered(clazz);
    }

    /**
     * Returns a {@link Role} with the specified ID or it returns null.
     * 
     * @param uid
     * @return
     */
   /* public Role getRoleByUid(String id)
    {
	return crudService.queryUnique(clazz,
		ExpressionFactory.inExp(Role.UID_PROPERTY, uid));
    }*/

    @Override
    public List<Role> findAllRoles()
    {
	return findAll();
    }

}
