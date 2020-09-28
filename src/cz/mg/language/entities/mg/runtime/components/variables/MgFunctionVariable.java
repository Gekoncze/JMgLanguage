package cz.mg.language.entities.mg.runtime.components.variables;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.mg.runtime.MgVariable;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;


public class MgFunctionVariable extends MgMemberVariable {
    public MgFunctionVariable(ReadableText name) {
        super(name);
    }

    public MgFunctionVariable(ReadableText name, MgDatatype datatype) {
        super(name, datatype);
    }
}
