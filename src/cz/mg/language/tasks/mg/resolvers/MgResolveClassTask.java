package cz.mg.language.tasks.mg.resolvers;

import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.mg.logical.components.MgLogicalClass;
import cz.mg.language.entities.mg.logical.components.MgLogicalLocation;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.components.MgLocation;
import cz.mg.language.entities.mg.runtime.components.types.MgClass;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.other.MgVariable;


public class MgResolveClassTask extends MgResolverTask {
    @Input
    private final MgLogicalLocation logicalLocation;

    @Input
    private final MgLogicalClass logicalClass;

    @Input
    private final MgLocation location;

    @Input
    private final MgClass clazz;

    @Subtask
    private MgResolveUsagesTask resolveUsagesTask;

    public MgResolveClassTask(MgLogicalLocation logicalLocation, MgLogicalClass logicalClass, MgLocation location, MgClass clazz) {
        this.logicalLocation = logicalLocation;
        this.logicalClass = logicalClass;
        this.location = location;
        this.clazz = clazz;
    }

    @Override
    protected void onRun() {
        resolveUsagesTask = new MgResolveUsagesTask(location, logicalClass.getContext());
        resolveUsagesTask.onRun();
        clazz.setStamps(todo); // TODO
        clazz.setClasses(resolveBaseClasses());
        clazz.setVariables(resolveVariables());
        clazz.setFunctions(resolveFunctions());
    }

    private Array<MgClass> resolveBaseClasses(){
        Array<MgClass> classes = new Array<>(logicalClass.getBaseClasses().count());
        int i = 0;
        for(ReadableText name : logicalClass.getBaseClasses()){
            classes.set(resolveBaseClass(name), i);
            i++;
        }
        return classes;
    }

    private MgClass resolveBaseClass(ReadableText name){
        List<MgClass> classes = resolveBaseClassAll(name);
        if(classes.count() <= 0){
            throw new LanguageException("Could not find class " + name + ".");
        } else if(classes.count() == 1){
            return classes.getFirst();
        } else {
            throw new LanguageException("Class " + name + " is ambiguous.");
        }
    }

    private List<MgClass> resolveBaseClassAll(ReadableText name){
        List<MgClass> classes = new List<>();
        for(MgComponent component : resolveUsagesTask.getComponents()){
            if(component.getName().equals(name)){
                if(component instanceof MgClass){
                    classes.addLast((MgClass) component);
                }
            }
        }
        return classes;
    }

    private Array<MgVariable> resolveVariables(){
        Array<MgVariable> variables = new Array<>(logicalClass.getVariables().count());
        todo;
        return variables;
    }

    private Array<MgFunction> resolveFunctions(){
        Array<MgFunction> functions = new Array<>(logicalClass.getFunctions().count());
        todo;
        return functions;
    }
}
