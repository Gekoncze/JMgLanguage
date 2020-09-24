package cz.mg.language.entities.mg.logical.parts.commands;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.storage.Value;


public class MgLogicalContinueCommand extends MgLogicalCommand {
    @Value
    private ReadableText target;

    public MgLogicalContinueCommand() {
    }

    public MgLogicalContinueCommand(ReadableText target) {
        this.target = target;
    }

    public ReadableText getTarget() {
        return target;
    }

    public void setTarget(ReadableText target) {
        this.target = target;
    }
}
