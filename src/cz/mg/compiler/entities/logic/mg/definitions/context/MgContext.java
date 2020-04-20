package cz.mg.compiler.entities.logic.mg.definitions.context;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.compiler.annotations.entity.Part;
import cz.mg.compiler.annotations.entity.Value;
import cz.mg.compiler.entities.logic.mg.MgEntity;
import cz.mg.compiler.entities.logic.mg.MgNamed;


public class MgContext extends MgEntity implements MgNamed {
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
