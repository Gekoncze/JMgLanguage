package cz.mg.language.entities.logic.mg.definitions.context;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.Named;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.logic.mg.MgLogicalEntity;


public class MgContext extends MgLogicalEntity implements Named {
    @Value
    private final ReadableText name;

    @Part
    private final List<MgUsing> usings = new List<>();

    public MgContext(ReadableText name) {
        this.name = name;
    }

    @Override
    public ReadableText getName() {
        return name;
    }

    public List<MgUsing> getUsings() {
        return usings;
    }
}
