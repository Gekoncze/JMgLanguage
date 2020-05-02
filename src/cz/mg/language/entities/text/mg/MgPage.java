package cz.mg.language.entities.text.mg;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.text.common.Page;


public class MgPage extends Page {
    @Part
    private final List<MgBlock> blocks = new List();

    public MgPage() {
    }

    public List<MgBlock> getBlocks() {
        return blocks;
    }
}
