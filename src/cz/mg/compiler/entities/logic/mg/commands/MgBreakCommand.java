package cz.mg.compiler.entities.logic.mg.commands;

import cz.mg.collections.text.ReadableText;
import cz.mg.compiler.annotations.entity.Value;


public class MgBreakCommand extends MgCommand {
    @Value
    private final ReadableText targetName;

    public MgBreakCommand() {
        this(null);
    }

    public MgBreakCommand(ReadableText targetName) {
        this.targetName = targetName;
    }

    public ReadableText getTargetName() {
        return targetName;
    }
}
