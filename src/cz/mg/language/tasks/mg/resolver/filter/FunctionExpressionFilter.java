package cz.mg.language.tasks.mg.resolver.filter;

import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.annotations.storage.Link;
import cz.mg.language.entities.mg.runtime.MgObject;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgFunction;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;
import cz.mg.language.tasks.mg.resolver.context.Context;


public class FunctionExpressionFilter extends AbstractClassFilter<MgFunction> {
    @Optional @Link
    private final ReadableArray<MgInputConnector> parentInputInterface;

    @Optional @Link
    private final ReadableArray<MgOutputConnector> childrenOutputInterface;

    @Optional @Link
    private final MgDatatype self;

    public FunctionExpressionFilter(
        @Mandatory Context context,
        @Optional ReadableText name,
        @Optional ReadableArray<MgInputConnector> parentInputInterface,
        @Optional ReadableArray<MgOutputConnector> childrenOutputInterface
    ) {
        this(context, name, parentInputInterface, childrenOutputInterface, null);
    }

    public FunctionExpressionFilter(
        @Mandatory Context context,
        @Optional ReadableText name,
        @Optional ReadableArray<MgInputConnector> parentInputInterface,
        @Optional ReadableArray<MgOutputConnector> childrenOutputInterface,
        @Optional MgDatatype self
    ) {
        super(context, name, MgFunction.class);
        this.parentInputInterface = getRemainingConnectors(parentInputInterface);
        this.childrenOutputInterface = childrenOutputInterface;
        this.self = self;
    }

    @Override
    protected boolean filter(MgObject object, ReadableText localName) {
        if(super.filter(object, localName)){
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
                MgDatatype inputDatatype = parentInputInterface.get(i).getDatatype();
                MgDatatype outputDatatype = function.getOutput().get(i).getDatatype();
                if(!MgDatatype.isCompatible(inputDatatype, outputDatatype)) return false;
            }
        }

        if(childrenOutputInterface != null){
            if(self == null){
                if(function.getInput().count() != childrenOutputInterface.count()) return false;
                for(int i = 0; i < function.getInput().count(); i++){
                    MgDatatype inputDatatype = function.getInput().get(i).getDatatype();
                    MgDatatype outputDatatype = childrenOutputInterface.get(i).getDatatype();
                    if(!MgDatatype.isCompatible(inputDatatype, outputDatatype)) return false;
                }
            } else {
                if(function.getInput().count() != childrenOutputInterface.count() + 1) return false;
                for(int i = 0; i < function.getInput().count(); i++){
                    MgDatatype inputDatatype = function.getInput().get(i).getDatatype();
                    MgDatatype outputDatatype = i == 0
                        ? self
                        : childrenOutputInterface.get(i-1).getDatatype();
                    if(!MgDatatype.isCompatible(inputDatatype, outputDatatype)) return false;
                }
            }
        }

        return true;
    }
}
