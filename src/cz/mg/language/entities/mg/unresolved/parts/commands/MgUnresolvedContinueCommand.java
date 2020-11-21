package cz.mg.language.entities.mg.unresolved.parts.commands;

import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Value;


public class MgUnresolvedContinueCommand extends MgUnresolvedCommand {
    @Optional @Value
    private ReadableText target;

    public MgUnresolvedContinueCommand() {
    }

    public MgUnresolvedContinueCommand(ReadableText target) {
        this.target = target;
    }

    public ReadableText getTarget() {
        return target;
    }

    public void setTarget(ReadableText target) {
        this.target = target;
    }
}
