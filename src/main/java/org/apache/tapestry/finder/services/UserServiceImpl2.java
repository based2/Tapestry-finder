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
import org.apache.tapestry.finder.entities.TapestryVersion;
import org.apache.tapestry.finder.entities.User;
import org.apache.tapestry5.ioc.annotations.Inject;

import org.apache.cayenne.exp.ExpressionFactory;
import org.apache.cayenne.query.Ordering;
import org.apache.cayenne.query.SortOrder;

/**
 * Service for all {@link User} related functionality. See also
 * {@link GenericService}
 */
public class UserServiceImpl2 implements UserService
{

    private static final String INVITE_STRING = "Invited";
    @SuppressWarnings("rawtypes")
    @Inject
    private CayenneCrudServiceDAO crudService;
    private final static Class<User> clazz = User.class;

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.apache.tapestry.finder.services.UserService#findByUserId(java.lang
     * .String)
     */

    @SuppressWarnings("unchecked")
    public User findById(Integer id)
    {
	return (User) crudService.findById(clazz, id);
    }

    /**
     * Find and return the {@link User} instance with the given userId
     * 
     * returns the User, if found, or else null
     */
    @SuppressWarnings("unchecked")
    public User findByUserId(String userId)
    {
	return (User) crudService.queryUnique(clazz, 
		ExpressionFactory.inExp(User.USER_ID_PROPERTY, userId));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.tapestry.finder.services.UserService#getAllUsers()
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAll()
    {
	 return crudService.queryList(clazz, 
		 ExpressionFactory.noMatchExp(User.USER_ID_PROPERTY, INVITE_STRING),
		 new Ordering(User.LAST_NAME_PROPERTY, SortOrder.ASCENDING_INSENSITIVE));
    }

}
