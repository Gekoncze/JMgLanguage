package cz.mg.language.entities.mg.logical.parts;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;


public class MgLogicalContext extends MgLogicalPart {
    @Value
    private ReadableText label;

    @Part
    private final List<MgLogicalUsage> usages = new List<>();

    public MgLogicalContext() {
    }

    public MgLogicalContext(ReadableText label) {
        this.label = label;
    }

    public ReadableText getLabel() {
        return label;
    }

    public void setLabel(ReadableText label) {
        this.label = label;
    }

    public List<MgLogicalUsage> getUsages() {
        return usages;
    }
}
