package cz.mg.language.entities.logic.mg.parts;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;


public class MgContextL extends MgPartL {
    @Value
    private final ReadableText label;

    @Part
    private final List<MgUsageL> usages = new List<>();

    public MgContextL() {
        this(null);
    }

    public MgContextL(ReadableText label) {
        this.label = label;
    }

    public List<MgUsageL> getUsages() {
        return usages;
    }
}
