package cz.mg.language.entities.c.logical.parts;

import cz.mg.collections.list.List;
import cz.mg.annotations.storage.Value;


public class CPointer extends CPart {
    @Value
    private final List<CModifier> modifers = new List<>();

    public CPointer() {
    }

    public List<CModifier> getModifers() {
        return modifers;
    }
}
