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
package org.apache.tapestry.finder.dal;

import java.util.List;

import org.apache.tapestry.finder.entities.ComponentEntry;
import org.apache.tapestry.finder.entities.EntryType;
import org.apache.tapestry.finder.entities.SourceType;

/**
 * Service for creating, managing and searching for {@link ComponentEntry}
 * entities. See also {@link GenericService}
 * 
 */
public interface CayenneDataService<T> {
	
	/**
	 * Create a new {@link ComponentEntry} object, not yet persisted to the
	 * database
	 * 
	 * @return the new object
	 */
	public ComponentEntry create();
	
	/**
	 * Find all {@link ComponentEntry}s, and return in alphabetical order by
	 * name
	 * 
	 * @return the list of components
	 */
	public List<ComponentEntry> findAll();

	/**
	 * Find all {@link ComponentEntry}s that match the given {@link EntryType}
	 * and at least one of the given {@link SourceType}s.
	 *  
	 * @param entryType the EntryType to match, or null to match all
	 * @param sourceTypes list of source types to match on, or null to match all
	 * @return a list of matching ComponentEntry objects, in alphabetical order
	 * by name
	 */
	public List<ComponentEntry> findByType(EntryType entryType,
			List<SourceType> sourceTypes);

	/**
	 * Find all {@link ComponentEntry}s that are eligible to be parents of the
	 * given entry, and return in alphabetical order by name. An entry can't be
	 * a parent of other entries of the same type.
	 * 	 
	 * @param entry
	 * @return the list of components
	 */
	public List<ComponentEntry> findParentCandidates(ComponentEntry entry);

}