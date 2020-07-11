package cz.mg.language.entities.mg.logical.architecture;

import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.entities.mg.logical.Buildin;


public class MgLogicalBuildinLocation extends MgLogicalLocation implements Buildin {
    public MgLogicalBuildinLocation(String name) {
        super(new ReadonlyText(name));
    }
}
