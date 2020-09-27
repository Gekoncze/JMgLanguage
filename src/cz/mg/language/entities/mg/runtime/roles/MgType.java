package cz.mg.language.entities.mg.runtime.roles;


public interface MgType extends MgComponent {
    // Checks if this type is sub-type of given type.
    // Examples:
    //     cat.is(animal) = true
    //     dog.is(animal) = true
    //     plant.is(animal) = false
    //     animal.is(cat) = false
    boolean is(MgType baseType);
}
