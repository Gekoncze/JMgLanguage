package cz.mg.language.entities.mg.logical.components.buidin;

import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.entities.mg.logical.Buildin;
import cz.mg.language.entities.mg.logical.components.MgLogicalLocation;


public class MgLogicalBuildinLocation extends MgLogicalLocation implements Buildin {
    public MgLogicalBuildinLocation(String name) {
        super(new ReadonlyText(name));
    }
}
