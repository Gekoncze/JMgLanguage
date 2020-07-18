package cz.mg.language.tasks.mg.resolver;

import cz.mg.collections.list.List;
import cz.mg.collections.list.ListItem;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.MgLogicalUsage;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.components.MgLocation;
import cz.mg.language.entities.mg.runtime.objects.MgObject;


public class MgResolveUsageTask extends MgResolverTask {
    @Input
    private final MgLogicalUsage usage;

    @Input
    private final MgLocation location;

    @Output
    private MgComponent component;

    public MgResolveUsageTask(MgLogicalUsage usage, MgLocation location) {
        this.usage = usage;
        this.location = location;
    }

    public MgComponent getComponent() {
        return component;
    }

    @Override
    protected void onRun() {
        MgLocation currentLocation = location;
        for(ListItem<ReadableText> nameItem = usage.getPath().getFirstItem(); nameItem != null; nameItem = nameItem.getNextItem()){
            ReadableText name = nameItem.get();
            MgComponent currentComponent = find(currentLocation, name);
            if(nameItem == usage.getPath().getLastItem()){
                component = currentComponent;
            } else {
                if(currentComponent instanceof MgLocation){
                    currentLocation = (MgLocation) currentComponent;
                } else {
                    throw new LanguageException(notFoundMessage() + " ('" + name + "' is not location)");
                }
            }
        }

        if(component == null){
            throw new LanguageException(notFoundMessage());
        }
    }

    private MgComponent find(MgLocation location, ReadableText name){
        List<MgComponent> results = findAll(location, name);
        if(results.count() <= 0){
            throw new LanguageException(notFoundMessage() + " (at '" + name + "')");
        } else if(results.count() == 1){
            return results.getFirst();
        } else {
            throw new LanguageException(ambiguousMessage() + " (" + results.count() + " candidates for " + name + ")");
        }
    }

    private List<MgComponent> findAll(MgLocation location, ReadableText name){
        List<MgComponent> results = new List<>();
        for(MgObject object : location.getObjects()){
            if(object instanceof MgComponent){
                MgComponent component = (MgComponent) object;
                if(component.getName().equals(name)){
                    results.addLast(component);
                }
            }
        }
        return results;
    }

    private String ambiguousMessage(){
        return "Usage " + usageMessage() + " is ambiguous.";
    }

    private String notFoundMessage(){
        return "Could not find target for usage " + usageMessage() + ".";
    }

    private String usageMessage(){
        return "'" + usage.getPath().toText(".") + "'";
    }
}
