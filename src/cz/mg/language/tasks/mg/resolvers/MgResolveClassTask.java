package cz.mg.language.tasks.mg.resolvers;

import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.mg.logical.components.MgLogicalClass;
import cz.mg.language.entities.mg.logical.components.MgLogicalFunction;
import cz.mg.language.entities.mg.logical.components.MgLogicalLocation;
import cz.mg.language.entities.mg.logical.parts.MgLogicalVariable;
import cz.mg.language.entities.mg.runtime.components.MgLocation;
import cz.mg.language.entities.mg.runtime.components.MgStamp;
import cz.mg.language.entities.mg.runtime.components.types.MgClass;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.components.types.MgType;
import cz.mg.language.entities.mg.runtime.other.MgDatatype;
import cz.mg.language.entities.mg.runtime.other.MgVariable;
import cz.mg.language.tasks.mg.resolvers.contexts.ClassContext;


public class MgResolveClassTask extends MgResolverTask {
    @Input
    private final MgLogicalLocation logicalLocation;

    @Input
    private final MgLogicalClass logicalClass;

    @Input
    private final MgLocation location;

    @Input
    private final MgClass clazz;

    @Part
    private Context context;

    @Subtask
    private final List<MgResolveFunctionTask> resolveFunctionTasks = new List<>();

    public MgResolveClassTask(MgLogicalLocation logicalLocation, MgLogicalClass logicalClass, MgLocation location, MgClass clazz, Context outerContext) {
        this.logicalLocation = logicalLocation;
        this.logicalClass = logicalClass;
        this.location = location;
        this.clazz = clazz;
        this.context = new ClassContext(outerContext, clazz);
    }

    @Override
    protected void onRun() {
        clazz.setStamps(resolveStamps());
        clazz.setClasses(resolveBaseClasses());
        clazz.setVariables(resolveVariables());
        clazz.setFunctions(resolveFunctions());
    }

    private Array<MgStamp> resolveStamps(){
        Array<MgStamp> stamps = new Array<>(logicalClass.getStamps().count());
        int i = 0;
        for(ReadableText name : logicalClass.getStamps()){
            Filter<MgStamp> filter = new Filter<>(context, MgStamp.class, name);
            stamps.set(filter.find(), i);
            i++;
        }
        return stamps;
    }

    private Array<MgClass> resolveBaseClasses(){
        Array<MgClass> classes = new Array<>(logicalClass.getBaseClasses().count());
        int i = 0;
        for(ReadableText name : logicalClass.getBaseClasses()){
            Filter<MgClass> filter = new Filter<>(context, MgClass.class, name);
            classes.set(filter.find(), i);
            i++;
        }
        return classes;
    }

    private Array<MgVariable> resolveVariables(){
        Array<MgVariable> variables = new Array<>(logicalClass.getVariables().count());
        int i = 0;
        for(MgLogicalVariable logicalVariable : logicalClass.getVariables()){
            Filter<MgType> filter = new Filter<>(context, MgType.class, logicalVariable.getType());
            MgDatatype datatype = new MgDatatype(
                filter.find(),
                MgDatatype.Storage.valueOf(logicalVariable.getStorage().name()),
                MgDatatype.Requirement.valueOf(logicalVariable.getRequirement().name())
            );
            MgVariable variable = new MgVariable(logicalVariable.getName(), datatype);
            variables.set(variable, i);
            i++;
        }
        return variables;
    }

    private Array<MgFunction> resolveFunctions(){
        Array<MgFunction> functions = new Array<>(logicalClass.getFunctions().count());
        int i = 0;
        for(MgLogicalFunction function : logicalClass.getFunctions()){
            resolveFunctionTasks.addLast(new MgResolveFunctionTask(todo));
            i++;
        }
        return functions;
    }
}
