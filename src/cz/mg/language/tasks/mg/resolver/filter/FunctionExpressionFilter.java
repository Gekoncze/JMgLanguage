package cz.mg.language.tasks.mg.resolver.filter;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.tasks.mg.resolver.Context;
import cz.mg.language.tasks.mg.resolver.command.expression.Matcher;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;


public class FunctionExpressionFilter extends ClassFilter<MgFunction> {
    @Optional @Link
    private final InputInterface parentInputInterface;

    @Optional @Link
    private final OutputInterface childrenOutputInterface;

    public FunctionExpressionFilter(
        @Mandatory Context context,
        @Optional ReadableText name,
        @Optional InputInterface parentInputInterface,
        @Optional OutputInterface childrenOutputInterface
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
        if(parentInputInterface != null){
            if(function.getOutput().count() > parentInputInterface.getRemainingConnectors().count()) return false;
            for(int i = 0; i < function.getOutput().count(); i++){
                MgDatatype inputDatatype = parentInputInterface.getRemainingConnectors().get(i).getRequestedDatatype();
                MgDatatype outputDatatype = function.getOutput().get(i).getDatatype();
                if(!Matcher.matches(inputDatatype, outputDatatype)) return false;
            }
        }

        if(childrenOutputInterface != null){
            if(function.getInput().count() != childrenOutputInterface.getConnectors().count()) return false;
            for(int i = 0; i < function.getInput().count(); i++){
                MgDatatype inputDatatype = function.getInput().get(i).getDatatype();
                MgDatatype outputDatatype = childrenOutputInterface.getConnectors().get(i).getRequestedDatatype();
                if(!Matcher.matches(inputDatatype, outputDatatype)) return false;
            }
        }

        return true;
    }
}
