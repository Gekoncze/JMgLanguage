package cz.mg.language.tasks.mg.resolver.contexts;

import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;
import cz.mg.collections.list.ReadableList;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.tasks.mg.resolver.Context;
import cz.mg.language.tasks.mg.resolver.filter.ClassFilter;


public class OperatorCache {
    @Part
    private final Array<List<MgFunction>> functions;

    public OperatorCache(Context context) {
        if(context != null){
            // find all functions available in this context
            ClassFilter<MgFunction> filter = new ClassFilter<>(context, null, MgFunction.class);
            ReadableList<MgFunction> availableFunctions = filter.findAll();

            // fund the min and max priority of the available functions
            int minOperatorPriority = 0;
            int maxOperatorPriority = 0;
            for(MgFunction function : availableFunctions){
                if(function.getOperator() != null){
                    minOperatorPriority = Math.min(minOperatorPriority, function.getPriority());
                    maxOperatorPriority = Math.max(maxOperatorPriority, function.getPriority());
                }
            }

            // create array of prioritized function lists
            this.functions = new Array<>(maxOperatorPriority - minOperatorPriority + 1);
            for(int i = 0; i < this.functions.count(); i++){
                this.functions.set(new List<>(), i);
            }

            // fill the prioritized function list with found operator functions
            for(MgFunction function : availableFunctions){
                if(function.getOperator() != null){
                    int i = function.getPriority() - minOperatorPriority;
                    this.functions.get(i).addLast(function);
                }
            }
        } else {
            this.functions = new Array<>();
        }
    }

    public Array<List<MgFunction>> getFunctions() {
        return functions;
    }
}
