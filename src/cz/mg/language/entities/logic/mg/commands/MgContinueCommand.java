package cz.mg.language.entities.logic.mg.commands;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Value;


public class MgContinueCommand extends MgCommand {
    @Value
    private final ReadableText targetName;

    public MgContinueCommand() {
        this(null);
    }

    public MgContinueCommand(ReadableText targetName) {
        this.targetName = targetName;
    }

    public ReadableText getTargetName() {
        return targetName;
    }
}
