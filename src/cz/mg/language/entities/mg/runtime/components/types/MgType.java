package cz.mg.language.entities.mg.runtime.components.types;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.mg.runtime.components.MgComponent;


public abstract class MgType extends MgComponent {
    public MgType(ReadableText name) {
        super(name);
    }

    // Checks if this type is sub-type of given type.
    // Examples:
    //     cat.is(animal) = true
    //     dog.is(animal) = true
    //     plant.is(animal) = false
    //     animal.is(cat) = false
    public abstract boolean is(MgType baseType);
}
