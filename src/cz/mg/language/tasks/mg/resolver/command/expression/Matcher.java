package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.ReadableCollection;
import cz.mg.language.entities.mg.runtime.components.types.MgType;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;

import java.util.Iterator;


public class Matcher {
    public static boolean matchesPartial(ReadableCollection<MgDatatype> lvalue, MgDatatype rvalue){
        if(lvalue == null || rvalue == null) return true;
        if(lvalue.count() < 1) return false;
        Iterator<MgDatatype> literator = lvalue.iterator();
        if(!matches(literator.next(), rvalue)) return false;
        return true;
    }

    public static boolean matchesPartial(ReadableCollection<MgDatatype> lvalue, ReadableCollection<MgDatatype> rvalue){
        if(lvalue == null || rvalue == null) return true;
        if(lvalue.count() < rvalue.count()) return false;
        Iterator<MgDatatype> literator = lvalue.iterator();
        Iterator<MgDatatype> riterator = rvalue.iterator();
        while(literator.hasNext() && riterator.hasNext()){
            if(!matches(literator.next(), riterator.next())) return false;
        }
        return true;
    }

    public static boolean matches(ReadableCollection<MgDatatype> lvalue, ReadableCollection<MgDatatype> rvalue){
        if(lvalue == null || rvalue == null) return true;
        if(lvalue.count() != rvalue.count()) return false;
        Iterator<MgDatatype> literator = lvalue.iterator();
        Iterator<MgDatatype> riterator = rvalue.iterator();
        while(literator.hasNext() && riterator.hasNext()){
            if(!matches(literator.next(), riterator.next())) return false;
        }
        return true;
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
