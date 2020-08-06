package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.array.ReadableArray;
import cz.mg.language.entities.mg.runtime.components.MgVariable;
import cz.mg.language.entities.mg.runtime.components.types.MgType;
import cz.mg.language.entities.mg.runtime.other.MgDatatype;


public class Matcher {
    public static boolean matches(Expression parent, Expression child){
        if(parent == null || child == null) return true;
        return matches(parent.getInput(), child.getOutput());
    }

    public static boolean matches(ReadableArray<MgVariable> parent, ReadableArray<MgVariable> child){
        if(parent == null || child == null) return true;
        if(parent.count() != child.count()) return false;
        for(int i = 0; i < parent.count(); i++){
            if(!matches(parent.get(i), child.get(i))) return false;
        }
        return true;
    }

    public static boolean matches(MgVariable parent, MgVariable child){
        return matches(parent.getDatatype(), child.getDatatype());
    }

    public static boolean matches(MgDatatype parent, MgDatatype child){
        boolean type = matches(parent.getType(), child.getType());
        boolean requirement = matches(parent.getRequirement(), child.getRequirement());
        boolean storage = matches(parent.getStorage(), child.getStorage());
        return type && requirement && storage;
    }

    public static boolean matches(MgType parent, MgType child){
        return child.is(parent);
    }

    public static boolean matches(MgDatatype.Requirement parent, MgDatatype.Requirement child){
        if(child == parent) return true;
        if(child == MgDatatype.Requirement.MANDATORY && parent == MgDatatype.Requirement.OPTIONAL) return true;
        return false;
    }

    public static boolean matches(MgDatatype.Storage parent, MgDatatype.Storage child){
        return parent == child;
    }
}
