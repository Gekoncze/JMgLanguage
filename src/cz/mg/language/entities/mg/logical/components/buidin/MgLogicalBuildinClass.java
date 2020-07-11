package cz.mg.language.entities.mg.logical.components.buidin;

import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.entities.mg.logical.Buildin;
import cz.mg.language.entities.mg.logical.components.MgLogicalClass;


public class MgLogicalBuildinClass extends MgLogicalClass implements Buildin {
    public MgLogicalBuildinClass(String name) {
        super(new ReadonlyText(name));
        getStamps().addLast(new ReadonlyText("public"));
    }
}
