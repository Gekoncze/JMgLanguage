package cz.mg.language.entities.mg.runtime.components.variables;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.mg.runtime.components.types.MgStructuredType;


public class MgTypeVariable extends MgInstanceVariable {
    public MgTypeVariable(ReadableText name, MgStructuredType parent) {
        super(name, parent);
    }
}
