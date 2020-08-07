package cz.mg.language.entities.mg.runtime.parts;

import cz.mg.collections.map.Map;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.entities.mg.runtime.components.MgVariable;


public class MgVariableTable {
    @Link
    private final Map<MgVariable, Integer> map = new Map<>();

    public MgVariableTable() {
    }

    public void set(MgVariable input, Integer output){
        map.set(input, output);
    }

    public Integer get(MgVariable input){
        return map.get(input);
    }
}
