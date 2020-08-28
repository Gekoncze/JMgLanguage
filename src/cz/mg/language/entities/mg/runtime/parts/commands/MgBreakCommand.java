package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;


public class MgBreakCommand extends MgCommand {
    @Optional @Part
    private final ReadableText name;

    public MgBreakCommand(ReadableText name) {
        this.name = name;
    }

    public ReadableText getName() {
        return name;
    }

    @Override
    public void run(MgFunctionObject functionObject) {
        // todo
    }
}
