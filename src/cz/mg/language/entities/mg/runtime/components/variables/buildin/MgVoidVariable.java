package cz.mg.language.entities.mg.runtime.components.variables.buildin;

import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.entities.mg.runtime.components.types.buildin.MgObjectType;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;


public class MgVoidVariable extends MgBuildinVariable {
    public static final MgVoidVariable INSTANCE = new MgVoidVariable();

    public static MgVoidVariable getInstance() {
        return INSTANCE;
    }

    static {
        MgBuildinVariable.ALL.addLast(getInstance());
    }

    private MgVoidVariable() {
        super(new ReadonlyText("void"), createDatatype());
    }

    private static MgDatatype createDatatype(){
        return new MgDatatype(
            MgObjectType.getInstance(),
            MgDatatype.Storage.INDIRECT,
            MgDatatype.Requirement.OPTIONAL
        );
    }
}
