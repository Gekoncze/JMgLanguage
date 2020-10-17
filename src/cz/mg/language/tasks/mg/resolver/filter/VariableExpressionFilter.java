package cz.mg.language.tasks.mg.resolver.filter;

import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.variables.MgVariable;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.entities.mg.runtime.MgObject;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;
import cz.mg.language.tasks.mg.resolver.context.Context;


public class VariableExpressionFilter extends AbstractClassFilter<MgVariable> {
    @Optional @Link
    private final ReadableArray<MgInputConnector> parentInputInterface;

    @Optional @Link
    private final ReadableArray<MgOutputConnector> childrenOutputInterface;

    public VariableExpressionFilter(
        @Mandatory Context context,
        @Optional ReadableText name,
        @Optional ReadableArray<MgInputConnector> parentInputInterface,
        @Optional ReadableArray<MgOutputConnector> childrenOutputInterface
    ) {
        super(context, name, MgVariable.class);
        this.parentInputInterface = getRemainingConnectors(parentInputInterface);
        this.childrenOutputInterface = childrenOutputInterface;
    }

    @Override
    protected boolean filter(MgObject object, ReadableText localName) {
        if(super.filter(object, localName)){
            if(object instanceof MgVariable){
                return filterVariable((MgVariable) object);
            }
        }
        return false;
    }

    private boolean filterVariable(MgVariable variable){
        // variables can have only one output value
        if(parentInputInterface != null){
            if(parentInputInterface.count() < 1) return false;
            MgDatatype inputDatatype = parentInputInterface.getFirst().getDatatype();
            MgDatatype outputDatatype = variable.getDatatype();
            if(!MgDatatype.isCompatible(inputDatatype, outputDatatype)) return false;
        }

        // variables cannot have any input value
        if(childrenOutputInterface != null){
            if(childrenOutputInterface.count() > 0) return false;
        }

        return true;
    }
}
