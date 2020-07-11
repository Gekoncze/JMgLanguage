package cz.mg.language.tasks.mg.resolvers;

import cz.mg.collections.list.ListItem;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalComponent;
import cz.mg.language.entities.mg.logical.components.MgLogicalLocation;
import cz.mg.language.entities.mg.logical.parts.MgLogicalUsage;


public class MgResolveUsageTask extends MgResolverTask {
    @Input
    private final MgLogicalUsage usage;

    @Input
    private final MgLogicalLocation root;

    @Output
    private MgLogicalComponent target;

    public MgResolveUsageTask(MgLogicalUsage usage, MgLogicalLocation root) {
        this.usage = usage;
        this.root = root;
    }

    public MgLogicalComponent getTarget() {
        return target;
    }

    @Override
    protected void onRun() {
        MgLogicalLocation location = root;
        for(ListItem<ReadableText> nameItem = usage.getPath().getFirstItem(); nameItem != null; nameItem = nameItem.getNextItem()){
            ReadableText name = nameItem.get();
            MgLogicalComponent component = find(location, name);
            if(nameItem == usage.getPath().getLastItem()){
                target = component;
            } else {
                if(component instanceof MgLogicalLocation){
                    location = (MgLogicalLocation) component;
                } else {
                    throw new LanguageException(errorMessage() + " ('" + name + "' is not location)");
                }
            }
        }

        if(target == null){
            throw new LanguageException(errorMessage());
        }
    }

    private MgLogicalComponent find(MgLogicalLocation location, ReadableText name){
        for(MgLogicalComponent component : location.getComponents()){
            if(component.getName().equals(name)){
                return component;
            }
        }
        throw new LanguageException(errorMessage() + " (at '" + name + "')");
    }

    private String errorMessage(){
        return "Could not find target for usage '" + usage.getPath().toText(".") + "'.";
    }
}
