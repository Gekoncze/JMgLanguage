package cz.mg.language.tasks.mg.builder.block.root.command;

import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.tasks.mg.builder.block.MgBuildBlockTask;


public abstract class MgBuildCommandTask extends MgBuildBlockTask {
    public MgBuildCommandTask(Block block) {
        super(block);
    }
}
