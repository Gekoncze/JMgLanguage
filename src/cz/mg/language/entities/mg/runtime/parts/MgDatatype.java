package cz.mg.language.entities.mg.runtime.parts;

import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.storage.Value;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.types.MgType;


public class MgDatatype extends MgPart {
    @Mandatory @Link
    private final MgType type;

    @Mandatory @Value
    private final Storage storage;

    @Mandatory @Value
    private final Requirement requirement;

    public MgDatatype(MgType type, Storage storage, Requirement requirement) {
        this.type = type;
        this.storage = storage;
        this.requirement = requirement;
    }

    public MgType getType() {
        return type;
    }

    public Storage getStorage() {
        return storage;
    }

    public Requirement getRequirement() {
        return requirement;
    }

    public enum Storage {
        DIRECT,
        INDIRECT,
        OTHER
    }

    public enum Requirement {
        OPTIONAL,
        MANDATORY
    }

    public static boolean isCompatible(@Mandatory MgDatatype lvalue, @Mandatory MgDatatype rvalue){
        if(!isCompatible(lvalue.getType(), rvalue.getType())) return false;
        if(!isCompatible(lvalue.getRequirement(), rvalue.getRequirement())) return false;
        if(!isCompatible(lvalue.getStorage(), rvalue.getStorage())) return false;
        return true;
    }

    private static boolean isCompatible(@Mandatory MgType lvalue, @Mandatory MgType rvalue){
        return rvalue.is(lvalue);
    }

    private static boolean isCompatible(@Mandatory MgDatatype.Requirement lvalue, @Mandatory MgDatatype.Requirement rvalue){
        if(rvalue == lvalue) return true;
        if(rvalue == MgDatatype.Requirement.MANDATORY && lvalue == MgDatatype.Requirement.OPTIONAL) return true;
        return false;
    }

    private static boolean isCompatible(@Mandatory MgDatatype.Storage lvalue, @Mandatory MgDatatype.Storage rvalue){
        return true;
    }
}
