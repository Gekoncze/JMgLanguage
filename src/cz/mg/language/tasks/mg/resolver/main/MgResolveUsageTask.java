package cz.mg.language.tasks.mg.resolver.main;

import cz.mg.collections.list.List;
import cz.mg.collections.list.ListItem;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.MgLogicalUsage;
import cz.mg.language.entities.mg.runtime.roles.MgComponent;
import cz.mg.language.entities.mg.runtime.components.MgLocation;
import cz.mg.language.entities.mg.runtime.roles.MgObject;
import cz.mg.language.tasks.mg.resolver.context.Context;
import cz.mg.language.tasks.mg.resolver.context.ApplicationContext;
import cz.mg.language.tasks.mg.resolver.command.utilities.Usage;


public class MgResolveUsageTask extends MgResolveTask {
    @Input
    private final MgLogicalUsage logicalUsage;

    @Output
    private Usage usage;

    public MgResolveUsageTask(Context context, MgLogicalUsage logicalUsage) {
        super(context);
        this.logicalUsage = logicalUsage;
    }

    public Usage getUsage() {
        return usage;
    }

    private ApplicationContext getApplicationContext(){
        Context context = getContext();
        while(context != null){
            if(context instanceof ApplicationContext){
                return (ApplicationContext) context;
            }
            context = context.getOuterContext();
        }
        throw new RuntimeException("Missing application context for usage resolution.");
    }

    @Override
    protected void onRun() {
        MgComponent component = null;
        MgLocation currentLocation = getApplicationContext().getApplication().getRoot();
        for(ListItem<ReadableText> nameItem = logicalUsage.getPath().getFirstItem(); nameItem != null; nameItem = nameItem.getNextItem()){
            ReadableText name = nameItem.get();
            MgComponent currentComponent = find(currentLocation, name);
            if(nameItem == logicalUsage.getPath().getLastItem()){
                component = currentComponent;
            } else {
                if(currentComponent instanceof MgLocation){
                    currentLocation = (MgLocation) currentComponent;
                } else {
                    throw new LanguageException(notFoundMessage() + " ('" + name + "' is not location)");
                }
            }
        }

        if(component == null) throw new LanguageException(notFoundMessage());
        usage = new Usage(component, logicalUsage.getAlias());
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
        for(MgObject object : location.getComponents()){
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
        return "'" + logicalUsage.getPath().toText(".") + "'";
    }
}
