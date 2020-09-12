package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.language.entities.mg.runtime.components.types.MgType;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;


public class Matcher {
    public static boolean matches(MgDatatype lvalue, MgDatatype rvalue){
        if(!matches(lvalue.getType(), rvalue.getType())) return false;
        if(!matches(lvalue.getRequirement(), rvalue.getRequirement())) return false;
        if(!matches(lvalue.getStorage(), rvalue.getStorage())) return false;
        return true;
    }

    private static boolean matches(MgType lvalue, MgType rvalue){
        return rvalue.is(lvalue);
    }

    private static boolean matches(MgDatatype.Requirement lvalue, MgDatatype.Requirement rvalue){
        if(rvalue == lvalue) return true;
        if(rvalue == MgDatatype.Requirement.MANDATORY && lvalue == MgDatatype.Requirement.OPTIONAL) return true;
        return false;
    }

    private static boolean matches(MgDatatype.Storage lvalue, MgDatatype.Storage rvalue){
        return lvalue == rvalue;
    }
}
