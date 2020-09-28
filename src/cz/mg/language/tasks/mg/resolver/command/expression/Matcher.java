package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.entities.mg.runtime.components.MgType;


public class Matcher {
    public static boolean matchesOptional(@Optional MgDatatype lvalue, @Optional MgDatatype rvalue){
        if(lvalue == null || rvalue == null) return true;
        else return matches(lvalue, rvalue);
    }

    public static boolean matches(@Mandatory MgDatatype lvalue, @Mandatory MgDatatype rvalue){
        if(!matches(lvalue.getType(), rvalue.getType())) return false;
        if(!matches(lvalue.getRequirement(), rvalue.getRequirement())) return false;
        if(!matches(lvalue.getStorage(), rvalue.getStorage())) return false;
        return true;
    }

    private static boolean matches(@Mandatory MgType lvalue, @Mandatory MgType rvalue){
        return rvalue.is(lvalue);
    }

    private static boolean matches(@Mandatory MgDatatype.Requirement lvalue, @Mandatory MgDatatype.Requirement rvalue){
        if(rvalue == lvalue) return true;
        if(rvalue == MgDatatype.Requirement.MANDATORY && lvalue == MgDatatype.Requirement.OPTIONAL) return true;
        return false;
    }

    private static boolean matches(@Mandatory MgDatatype.Storage lvalue, @Mandatory MgDatatype.Storage rvalue){
        return lvalue == rvalue;
    }
}
