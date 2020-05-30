package cz.mg.language.tasks.mg.builders.block;

import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.tasks.mg.builders.child.Children;
import cz.mg.language.tasks.mg.builders.pattern.Patterns;


public class MgBuildRootTask extends MgBuildBlockTask {
    private static final Patterns PATTERNS = new Patterns(
            // TODO
    );

    private static final Children CHILDREN = new Children(
            // TODO
    );

    public MgBuildRootTask(Block block) {
        super(block);
    }

    @Override
    public Patterns getPatterns() {
        return PATTERNS;
    }

    @Override
    public Children getChildren() {
        return CHILDREN;
    }
}
