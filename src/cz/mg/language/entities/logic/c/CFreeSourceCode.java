package cz.mg.language.entities.logic.c;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.logic.c.elements.CElement;


public class CFreeSourceCode extends CEntity {
    @Part
    private final List<CElement> elements = new List<>();

    public CFreeSourceCode() {
    }

    public List<CElement> getElements() {
        return elements;
    }
}
