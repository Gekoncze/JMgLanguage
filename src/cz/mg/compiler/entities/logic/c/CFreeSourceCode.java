package cz.mg.compiler.entities.logic.c;

import cz.mg.collections.list.List;
import cz.mg.compiler.annotations.entity.Part;
import cz.mg.compiler.entities.logic.c.elements.CElement;


public class CFreeSourceCode extends CEntity {
    @Part
    private final List<CElement> elements = new List<>();

    public CFreeSourceCode() {
    }

    public List<CElement> getElements() {
        return elements;
    }
}
