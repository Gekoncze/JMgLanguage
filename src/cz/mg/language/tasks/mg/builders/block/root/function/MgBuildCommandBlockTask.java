package cz.mg.language.tasks.mg.builders.block.root.function;

import cz.mg.collections.ReadableCollection;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.tasks.mg.builders.block.MgBuildBlockTask;
import cz.mg.language.tasks.mg.builders.pattern.block.BlockPattern;
import cz.mg.language.tasks.mg.builders.pattern.part.PartPattern;


public class MgBuildCommandBlockTask extends MgBuildBlockTask {
    public MgBuildCommandBlockTask(Block block) {
        super(block);
    }

    @Override
    public ReadableCollection<PartPattern> getPartPatterns() {
        return null; todo;
    }

    @Override
    public ReadableCollection<BlockPattern> getBlockPatterns() {
        return null; todo;
    }
}
