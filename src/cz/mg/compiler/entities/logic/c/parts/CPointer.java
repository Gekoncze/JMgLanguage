package cz.mg.compiler.entities.logic.c.parts;

import cz.mg.collections.list.List;
import cz.mg.compiler.annotations.entity.Value;


public class CPointer extends CPart {
    @Value
    private final List<CModifier> modifers = new List<>();

    public CPointer() {
    }

    public List<CModifier> getModifers() {
        return modifers;
    }
}
