package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.array.ReadableArray;
import cz.mg.language.entities.mg.runtime.components.MgVariable;
import cz.mg.language.entities.mg.runtime.components.types.MgType;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;


public class Matcher {
//    public static boolean matchesPartial(ReadableArray<MgVariable> lvalue, MgVariable rvalue){
//        if(lvalue == null || rvalue == null) return true;
//        if(lvalue.count() < 1) return false;
//        if(!matches(lvalue.getFirst(), rvalue)) return false;
//        return true;
//    }
//
//    public static boolean matchesPartial(ReadableArray<MgVariable> lvalue, ReadableArray<MgVariable> rvalue){
//        if(lvalue == null || rvalue == null) return true;
//        if(lvalue.count() < rvalue.count()) return false;
//        for(int i = 0; i < rvalue.count(); i++){
//            if(!matches(lvalue.get(i), rvalue.get(i))) return false;
//        }
//        return true;
//    }
//
//    public static boolean matches(ReadableArray<MgVariable> lvalue, ReadableArray<MgVariable> rvalue){
//        if(lvalue == null || rvalue == null) return true;
//        if(lvalue.count() != rvalue.count()) return false;
//        for(int i = 0; i < lvalue.count(); i++){
//            if(!matches(lvalue.get(i), rvalue.get(i))) return false;
//        }
//        return true;
//    }

    public static boolean matches(MgVariable lvalue, MgVariable rvalue){
        if(lvalue == null || rvalue == null) return true;
        return matches(lvalue.getDatatype(), rvalue.getDatatype());
    }

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
