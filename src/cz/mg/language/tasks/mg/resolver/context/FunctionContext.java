package cz.mg.language.tasks.mg.resolver.context;

import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.annotations.task.Cache;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.components.MgFunction;
import cz.mg.language.entities.mg.runtime.roles.MgObject;
import cz.mg.language.tasks.mg.resolver.command.utilities.VariableHelper;
import cz.mg.language.tasks.mg.resolver.command.utilities.OperatorCache;


public class FunctionContext extends ComponentContext {
    @Optional @Link
    private MgFunction function;

    @Optional @Cache
    private OperatorCache operatorCache;

    @Optional @Part
    private VariableHelper variableHelper;

    public FunctionContext(@Optional Context outerContext) {
        super(outerContext);
    }

    @Override
    public MgComponent getComponent() {
        return function;
    }

    public MgFunction getFunction() {
        return function;
    }

    public void setFunction(MgFunction function) {
        if(this.operatorCache != null) throw new RuntimeException();
        if(this.variableHelper != null) throw new RuntimeException();
        this.function = function;
    }

    public OperatorCache getOperatorCache() {
        if(operatorCache == null) operatorCache = new OperatorCache(this);
        return operatorCache;
    }

    public VariableHelper getVariableHelper() {
        if(this.variableHelper == null) this.variableHelper = VariableHelper.create(function);
        return variableHelper;
    }

    @Override
    public void forEachComponent(ObjectVisitor visitor) {
        if(function != null){
            for(MgObject object : function.getInput()){
                visitor.onVisitComponent(object, null);
            }

            for(MgObject object : function.getOutput()){
                visitor.onVisitComponent(object, null);
            }

            for(MgObject object : function.getLocal()){
                visitor.onVisitComponent(object, null);
            }
        }
    }
}
