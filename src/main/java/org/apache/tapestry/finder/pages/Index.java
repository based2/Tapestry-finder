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

package org.apache.tapestry.finder.pages;

import java.util.List;

import org.apache.tapestry.finder.components.EntryList;
import org.apache.tapestry.finder.encoders.SourceTypeEncoder;
import org.apache.tapestry.finder.entities.ComponentEntry;
import org.apache.tapestry.finder.entities.EntryType;
import org.apache.tapestry.finder.entities.SourceType;
import org.apache.tapestry.finder.services.EntryTypeService;
import org.apache.tapestry.finder.services.SourceTypeService;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.CleanupRender;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.SelectModelFactory;

/**
 * Start page of web application. Here we display and manage the high-level
 * selection menus, along with the initial list of entries (the latter appearing
 * within a zone)
 */
public class Index
{	
	@SuppressWarnings("unused")
	@Parameter
	@Property
	private EntryType selectedEntryType;
	
	@SuppressWarnings("unused")
	@Property
	private List<SourceType> selectedSourceTypes;
	
	@PageActivationContext
	private ComponentEntry selectedEntry;

	@SuppressWarnings("unused")
	@Property
	private SelectModel entryTypeSelectModel;

	@SuppressWarnings("unused")
	@Property
	private SelectModel sourceTypeSelectModel;

	@Inject
	private EntryTypeService entryTypeService;
	
	@Inject
	private SourceTypeService sourceTypeService;
	
	@Inject
	private SelectModelFactory selectModelFactory;
	
	@Inject
	private Request request;

	@Persist
	private String successMessage;

	@Persist
	private String failureMessage;

	@InjectComponent
	private EntryList entryList;

	public String getFailureMessage()
	{
		return failureMessage;
	}

	public ComponentEntry getSelectedEntry()
	{
		return selectedEntry;
	}
	
	public ValueEncoder<SourceType> getSourceTypeEncoder()
    {
        return new SourceTypeEncoder();
    }
	
	public String getSuccessMessage()
	{
		return successMessage;
	}
	
	/**
	 * As an event handler, respond to the form's PREPARE_FOR_RENDER event,
	 * doing setup actions prior to rendering the form.
	 */
	void onPrepareForRender()
	{
		// populate the list of entry types for the entry type drop-down menu
		List<EntryType> entryTypes = entryTypeService.findAll();

		// create a SelectModel from the list of entry types
		entryTypeSelectModel = selectModelFactory.create(entryTypes,
				EntryType.NAME_PLURAL_PROPERTY);

		// populate the list of entry types for the entry type drop-down menu
		List<SourceType> sourceTypes = sourceTypeService.findAll();

		// create a SelectModel from the list of source types
		sourceTypeSelectModel = selectModelFactory.create(sourceTypes,
				SourceType.NAME_PLURAL_PROPERTY);
	}
	
	/**
	 * As an event lister, respond to a selection from the "entryType"
	 * Select menu. Return the "entryList" component (to be put into a
	 * zone). If the event is not part of an AJAX zone update (i.e. the browser
	 * has JavaScript off) we return the whole page to be redrawn.
	 * 
	 * @return
	 */
	Object onValueChangedFromEntryType(EntryType entryType)
	{
		if (request.isXHR()) // an AJAX request?
		{
			selectedEntryType = entryType;
			entryList.setEntryType(entryType);
			return entryList; // return the entryList component
		}
		else
		{
			return null; // redraw the whole current page
		}
	}

	/**
	 * Empty out the messages so they don't display after the first time.
	 * Note: this is only required because I can't get
	 * @Persist(PersistenceConstants.FLASH) to work like I think it should 
	 */
	@CleanupRender
	void cleanup() {
		setFailureMessage("");
		setSuccessMessage("");
	}
	
	/**
	 * Handle the "Success" event from the CategorySelection form
	 * 
	 * @return the current page (redraw self)
	 */
	Object onSuccessFromCategorySelection()
	{
	    return this;
	}
	public void setFailureMessage(String string)
	{
		failureMessage = string;		
	}

	public void setSelectedEntry(ComponentEntry selectedEntry)
	{
		this.selectedEntry = selectedEntry;
	}

	public void setSuccessMessage(String string)
	{
		successMessage = string;		
	}


}
