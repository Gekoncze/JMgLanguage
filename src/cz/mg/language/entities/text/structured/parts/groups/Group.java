package cz.mg.language.entities.text.structured.parts.groups;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.text.structured.parts.CommonPart;


public abstract class Group extends CommonPart {
    @Part
    protected final List<CommonPart> parts = new List<>();

    public Group() {
    }

    public List<CommonPart> getParts() {
        return parts;
    }
}
