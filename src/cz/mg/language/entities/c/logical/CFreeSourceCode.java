package cz.mg.language.entities.c.logical;

import cz.mg.collections.list.List;
import cz.mg.annotations.storage.Part;
import cz.mg.language.entities.c.logical.elements.CElement;


public class CFreeSourceCode extends CLogicalEntity {
    @Part
    private final List<CElement> elements = new List<>();

    public CFreeSourceCode() {
    }

    public List<CElement> getElements() {
        return elements;
    }
}
