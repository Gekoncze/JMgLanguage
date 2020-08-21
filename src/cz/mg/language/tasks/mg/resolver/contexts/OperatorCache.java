package cz.mg.language.tasks.mg.resolver.contexts;

import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;
import cz.mg.collections.list.ReadableList;
import cz.mg.collections.map.Map;
import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.parts.MgOperator;
import cz.mg.language.tasks.mg.resolver.Context;
import cz.mg.language.tasks.mg.resolver.filter.OperatorFilter;


public class OperatorCache {
    @Part
    private final Array<List<MgFunction>> functions;

    @Part
    private final Map<ReadableText, MgOperator> operatorMap = new Map<>();

    public OperatorCache(Context context) {
        if(context != null){
            // find all functions available in this context
            OperatorFilter filter = new OperatorFilter(context);
            ReadableList<MgFunction> availableOperators = filter.findAll();

            // fund the min and max priority of the available functions
            int minOperatorPriority = 0;
            int maxOperatorPriority = 0;
            for(MgFunction operator : availableOperators){
                minOperatorPriority = Math.min(minOperatorPriority, operator.getOperator().getPriority());
                maxOperatorPriority = Math.max(maxOperatorPriority, operator.getOperator().getPriority());
            }

            // create array of prioritized function lists
            this.functions = new Array<>(maxOperatorPriority - minOperatorPriority + 1);
            for(int i = 0; i < this.functions.count(); i++){
                this.functions.set(new List<>(), i);
            }

            // fill the prioritized function list with found operator functions
            for(MgFunction function : availableOperators){
                if(function.getOperator() != null){
                    int i = function.getOperator().getPriority() - minOperatorPriority;
                    this.functions.get(i).addLast(function);
                } else {
                    this.functions.get(0).addLast(function);
                }
            }
        } else {
            this.functions = new Array<>();
        }
    }

    public Array<List<MgFunction>> getFunctions() {
        return functions;
    }

    private static final MgOperator UNKNOWN = new MgOperator(new ReadonlyText(""));
    public MgOperator findOperator(ReadableText operatorName){
        MgOperator operator = operatorMap.get(operatorName, UNKNOWN);
        if(operator == UNKNOWN){
            for(List<MgFunction> array : functions){
                for(MgFunction function : array){
                    if(function.getOperator().getName().equals(operatorName)){
                        if(operator == UNKNOWN){
                            operator = function.getOperator();
                        } else {
                            if(!isCompatible(operator, function.getOperator())){
                                throw new LanguageException("Ambiguous operator '" + operatorName + "'.");
                            }
                        }
                    }
                }
            }
            if(operator == UNKNOWN) operator = null;
            operatorMap.set(operatorName, operator);
        }
        return operator;
    }

    private static boolean isCompatible(MgOperator first, MgOperator second){
        return first.getType() == second.getType() && first.getPriority() == second.getPriority();
    }
}
