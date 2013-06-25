package org.apache.tapestry.unicorn.entities.auto;

import java.util.List;

import org.apache.cayenne.CayenneDataObject;
import org.apache.tapestry.unicorn.entities.Entry;

/**
 * Class _EntryType was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _EntryType extends CayenneDataObject {

    public static final String CSS_CLASS_PROPERTY = "cssClass";
    public static final String DESCRIPTION_PROPERTY = "description";
    public static final String ID_PROPERTY = "id";
    public static final String IS_CONTAINER_PROPERTY = "isContainer";
    public static final String NAME_PROPERTY = "name";
    public static final String NAME_PLURAL_PROPERTY = "namePlural";
    public static final String SORT_BY_PROPERTY = "sortBy";
    public static final String ENTRIES_PROPERTY = "entries";

    public static final String ID_PK_COLUMN = "ID";

    public void setCssClass(String cssClass) {
        writeProperty("cssClass", cssClass);
    }
    public String getCssClass() {
        return (String)readProperty("cssClass");
    }

    public void setDescription(String description) {
        writeProperty("description", description);
    }
    public String getDescription() {
        return (String)readProperty("description");
    }

    public void setId(Integer id) {
        writeProperty("id", id);
    }
    public Integer getId() {
        return (Integer)readProperty("id");
    }

    public void setIsContainer(Boolean isContainer) {
        writeProperty("isContainer", isContainer);
    }
    public Boolean getIsContainer() {
        return (Boolean)readProperty("isContainer");
    }

    public void setName(String name) {
        writeProperty("name", name);
    }
    public String getName() {
        return (String)readProperty("name");
    }

    public void setNamePlural(String namePlural) {
        writeProperty("namePlural", namePlural);
    }
    public String getNamePlural() {
        return (String)readProperty("namePlural");
    }

    public void setSortBy(Integer sortBy) {
        writeProperty("sortBy", sortBy);
    }
    public Integer getSortBy() {
        return (Integer)readProperty("sortBy");
    }

    public void addToEntries(Entry obj) {
        addToManyTarget("entries", obj, true);
    }
    public void removeFromEntries(Entry obj) {
        removeToManyTarget("entries", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<Entry> getEntries() {
        return (List<Entry>)readProperty("entries");
    }


}
