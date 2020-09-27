package cz.mg.language.tasks.mg.resolver.filter;

import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.roles.MgComponent;
import cz.mg.language.entities.mg.runtime.components.MgFunction;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.entities.mg.runtime.roles.MgObject;
import cz.mg.language.tasks.mg.resolver.context.Context;
import cz.mg.language.tasks.mg.resolver.command.expression.Matcher;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputConnector;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputConnector;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;


public class FunctionExpressionFilter extends AbstractClassFilter<MgFunction> {
    @Optional @Link
    private final ReadableArray<InputConnector> parentInputInterface;

    @Optional @Link
    private final ReadableArray<OutputConnector> childrenOutputInterface;

    @Optional @Link
    private final MgDatatype self;

    public FunctionExpressionFilter(
        @Mandatory Context context,
        @Optional ReadableText name,
        @Optional InputInterface parentInputInterface,
        @Optional OutputInterface childrenOutputInterface
    ) {
        this(context, name, parentInputInterface, childrenOutputInterface, null);
    }

    public FunctionExpressionFilter(
        @Mandatory Context context,
        @Optional ReadableText name,
        @Optional InputInterface parentInputInterface,
        @Optional OutputInterface childrenOutputInterface,
        @Optional MgDatatype self
    ) {
        super(context, name, MgFunction.class);
        this.parentInputInterface = parentInputInterface != null ? parentInputInterface.getRemainingConnectors() : null;
        this.childrenOutputInterface = childrenOutputInterface != null ? childrenOutputInterface.getConnectors() : null;
        this.self = self;
    }

    @Override
    protected boolean filter(MgObject object, ReadableText alias) {
        if(super.filter(object, alias)){
            if(object instanceof MgFunction){
                return filterFunction((MgFunction) object);
            }
        }
        return false;
    }

    private boolean filterFunction(MgFunction function){
        if(parentInputInterface != null){
            if(function.getOutput().count() > parentInputInterface.count()) return false;
            for(int i = 0; i < function.getOutput().count(); i++){
                MgDatatype inputDatatype = parentInputInterface.get(i).getRequestedDatatype();
                MgDatatype outputDatatype = function.getOutput().get(i).getDatatype();
                if(!Matcher.matches(inputDatatype, outputDatatype)) return false;
            }
        }

        if(childrenOutputInterface != null){
            if(self == null){
                if(function.getInput().count() != childrenOutputInterface.count()) return false;
                for(int i = 0; i < function.getInput().count(); i++){
                    MgDatatype inputDatatype = function.getInput().get(i).getDatatype();
                    MgDatatype outputDatatype = childrenOutputInterface.get(i).getRequestedDatatype();
                    if(!Matcher.matches(inputDatatype, outputDatatype)) return false;
                }
            } else {
                if(function.getInput().count() != childrenOutputInterface.count() + 1) return false;
                for(int i = 0; i < function.getInput().count(); i++){
                    MgDatatype inputDatatype = function.getInput().get(i).getDatatype();
                    MgDatatype outputDatatype = i == 0
                        ? self
                        : childrenOutputInterface.get(i-1).getRequestedDatatype();
                    if(!Matcher.matches(inputDatatype, outputDatatype)) return false;
                }
            }
        }

        return true;
    }
}
