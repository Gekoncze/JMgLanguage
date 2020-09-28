package cz.mg.language.entities.mg.runtime.components.variables;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;


public class MgClassVariable extends MgMemberVariable {
    public MgClassVariable(ReadableText name) {
        super(name);
    }

    public MgClassVariable(ReadableText name, MgDatatype datatype) {
        super(name, datatype);
    }
}
