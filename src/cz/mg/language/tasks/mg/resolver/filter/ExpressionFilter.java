package cz.mg.language.tasks.mg.resolver.filter;

import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.components.MgVariable;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.tasks.mg.resolver.Context;
import cz.mg.language.tasks.mg.resolver.command.expression.Matcher;


public class ExpressionFilter extends ClassFilter<MgComponent> {
    private static final ReadableArray<MgVariable> EMPTY = new Array<>();

    @Link
    private final ReadableArray<MgVariable> input;

    @Link
    private final ReadableArray<MgVariable> output;

    public ExpressionFilter(Context context, ReadableText name, ReadableArray<MgVariable> input, ReadableArray<MgVariable> output) {
        super(context, name, MgVariable.class, MgFunction.class);
        this.input = input;
        this.output = output;
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
        if(!Matcher.matches(EMPTY, input)) return false;
        if(!Matcher.matchesPartial(output, variable)) return false;
        return true;
    }

    private boolean filterFunction(MgFunction function){
        if(!Matcher.matches(function.getInput(), input)) return false;
        if(!Matcher.matchesPartial(output, function.getOutput())) return false;
        return true;
    }
}
