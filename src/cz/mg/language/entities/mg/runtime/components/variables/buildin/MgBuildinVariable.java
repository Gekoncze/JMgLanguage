package cz.mg.language.entities.mg.runtime.components.variables.buildin;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.mg.runtime.components.variables.MgGlobalVariable;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;


public abstract class MgBuildinVariable extends MgGlobalVariable {
    public static final List<MgBuildinVariable> ALL = new List<>();

    protected MgBuildinVariable(ReadableText name) {
        super(name);
    }

    protected MgBuildinVariable(ReadableText name, MgDatatype datatype) {
        super(name, datatype);
    }
}
