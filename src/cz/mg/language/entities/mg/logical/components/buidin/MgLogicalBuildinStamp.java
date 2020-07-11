package cz.mg.language.entities.mg.logical.components.buidin;

import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.entities.mg.logical.Buildin;
import cz.mg.language.entities.mg.logical.components.MgLogicalStamp;


public class MgLogicalBuildinStamp extends MgLogicalStamp implements Buildin {
    public MgLogicalBuildinStamp(String name) {
        super(new ReadonlyText(name));
        getStamps().addLast(new ReadonlyText("public"));
    }
}
