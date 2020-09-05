package cz.mg.language.tasks.mg.resolver.filter;

import cz.mg.collections.array.Array;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.components.MgVariable;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.tasks.mg.resolver.Context;
import cz.mg.language.tasks.mg.resolver.command.expression.Matcher;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;


public class ExpressionFilter extends ClassFilter<MgComponent> {
    @Optional @Value
    private final Integer parentOffset;

    @Optional @Link
    private final InputInterface parentInputInterface;

    @Optional @Link
    private final Array<OutputInterface> childrenOutputInterface;

    public ExpressionFilter(Context context, ReadableText name, Integer parentOffset, InputInterface parentInputInterface, Array<OutputInterface> childrenOutputInterface) {
        super(context, name, MgVariable.class, MgFunction.class);
        this.parentOffset = parentOffset;
        this.parentInputInterface = parentInputInterface;
        this.childrenOutputInterface = childrenOutputInterface;
    }

    @Override
    protected boolean filter(MgComponent component) {
        if(super.filter(component)){
            if(component instanceof MgVariable){
                return filterVariable((MgVariable) component);
            }

            if(component instanceof MgFunction){
                return filterFunction((MgFunction) component);
            }
        }
        return false;
    }

    private boolean filterVariable(MgVariable variable){
        // variables can have only one output value
        if(parentInputInterface != null){
            if(parentInputInterface.getConnectors().count() < parentOffset + 1) return false;
            if(!Matcher.matches(
                parentInputInterface.getConnectors().get(parentOffset).getRequestedDatatype(),
                variable.getDatatype()
            )) return false;
        }

        // variables cannot have any input value
        if(childrenOutputInterface != null){
            if(childrenOutputInterface.count() > 0) return false;
        }

        return true;
    }

    private boolean filterFunction(MgFunction function){
        // functions can have multiple output values
        if(parentInputInterface != null){
            int remainingParentConnectors = parentInputInterface.getConnectors().count() - parentOffset;
            if(function.getOutput().count() > remainingParentConnectors) return false;

            for(int i = 0; i < function.getOutput().count(); i++){
                int pi = parentOffset + i;
                if(!Matcher.matches(
                    parentInputInterface.getConnectors().get(pi).getRequestedDatatype(),
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
