package cz.mg.language.entities.mg.runtime.other;

import cz.mg.collections.map.Map;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;


public class MgOverrideTable {
    @Link
    private final Map<MgFunction, MgFunction> map = new Map<>();

    public MgOverrideTable() {
    }

    public void set(MgFunction input, MgFunction output){
        map.set(input, output);
    }

    public MgFunction get(MgFunction input){
        return map.get(input);
    }
}
