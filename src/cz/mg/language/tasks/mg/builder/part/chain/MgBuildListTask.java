package cz.mg.language.tasks.mg.builder.part.chain;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.entities.text.structured.Part;


public abstract class MgBuildListTask extends MgBuildChainTask {
    private static final ReadableText SEPARATOR = new ReadonlyText(",");

    public MgBuildListTask(List<Part> parts) {
        super(parts, SEPARATOR);
    }
}
