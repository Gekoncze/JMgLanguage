package cz.mg.language.tasks.mg.resolver.context;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.language.tasks.mg.resolver.context.component.structured.FunctionContext;


public class FunctionBodyContext extends ExecutableContext {
    public FunctionBodyContext(@Mandatory FunctionContext outerContext) {
        super(outerContext);
    }

    @Override
    public @Optional FunctionContext getOuterContext() {
        return (FunctionContext) super.getOuterContext();
    }
}
