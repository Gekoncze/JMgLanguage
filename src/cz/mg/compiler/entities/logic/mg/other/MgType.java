package cz.mg.compiler.entities.logic.mg.other;

import cz.mg.collections.text.ReadableText;
import cz.mg.compiler.annotations.entity.Link;
import cz.mg.compiler.annotations.entity.Value;
import cz.mg.compiler.entities.logic.mg.definitions.MgClass;
import cz.mg.compiler.entities.logic.mg.definitions.unresolved.MgUnresolvedClass;


public class MgType extends MgOther {
    @Link
    private final MgClass clazz;

    @Value
    private final MgModifier modifier;

    public MgType(ReadableText typename, MgModifier modifier) {
        this.clazz = new MgUnresolvedClass(typename);
        this.modifier = modifier;
    }

    public MgClass getClazz() {
        return clazz;
    }

    public MgModifier getModifier() {
        return modifier;
    }
}
