package cz.mg.language.tasks.mg.resolver.filter;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.tasks.mg.resolver.Context;
import cz.mg.language.tasks.mg.resolver.command.expression.Matcher;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;


public class FunctionExpressionFilter extends ClassFilter<MgFunction> {
    @Optional @Link
    private final InputInterface parentInputInterface;

    @Optional @Link
    private final List<OutputInterface> childrenOutputInterface;

    public FunctionExpressionFilter(
        @Mandatory Context context,
        @Optional ReadableText name,
        @Optional InputInterface parentInputInterface,
        @Optional List<OutputInterface> childrenOutputInterface
    ) {
        super(context, name, MgFunction.class);
        this.parentInputInterface = parentInputInterface;
        this.childrenOutputInterface = childrenOutputInterface;
    }

    @Override
    protected boolean filter(MgComponent component) {
        if(super.filter(component)){
            if(component instanceof MgFunction){
                return filterFunction((MgFunction) component);
            }
        }
        return false;
    }

    private boolean filterFunction(MgFunction function){
        // functions can have multiple output values
        if(parentInputInterface != null){
            if(function.getOutput().count() > parentInputInterface.getRemainingConnectors().count()) return false;

            for(int i = 0; i < function.getOutput().count(); i++){
                if(!Matcher.matches(
                    parentInputInterface.getRemainingConnectors().get(i).getRequestedDatatype(),
                    function.getOutput().get(i).getDatatype()
                )) return false;
            }
        }

        // functions can have multiple input values
        if(childrenOutputInterface != null){
            int functionInputOffset = 0;
            for(OutputInterface childOutputInterface : childrenOutputInterface){
                int remainingFunctionConnectors = function.getInput().count() - functionInputOffset;
                if(childOutputInterface.getConnectors().count() > remainingFunctionConnectors) return false;

                for(int i = 0; i < childOutputInterface.getConnectors().count(); i++){
                    int fi = functionInputOffset + i;
                    if(!Matcher.matches(
                        function.getInput().get(fi).getDatatype(),
                        childOutputInterface.getConnectors().get(i).getRequestedDatatype()
                    )) return false;
                }

                functionInputOffset += childOutputInterface.getConnectors().count();
            }
        }

        return true;
    }
}
