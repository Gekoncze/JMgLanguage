package cz.mg.language.entities.mg.runtime.components.variables;

import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Value;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;


public abstract class MgVariable extends MgComponent {
    @Optional @Value
    private MgDatatype datatype;

    public MgVariable(ReadableText name) {
        super(name);
    }

    public MgDatatype getDatatype() {
        return datatype;
    }

    public void setDatatype(MgDatatype datatype) {
        this.datatype = datatype;
    }
}
