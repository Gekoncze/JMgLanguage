package cz.mg.language.tasks.mg.resolver.contexts.utilities;

import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;
import cz.mg.collections.list.ReadableList;
import cz.mg.collections.map.Map;
import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.storage.Value;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.parts.MgOperator;
import cz.mg.language.tasks.mg.resolver.Context;
import cz.mg.language.tasks.mg.resolver.filter.OperatorFilter;


public class OperatorCache {
    @Mandatory @Value
    private final int minPriority;

    @Mandatory @Value
    private final int maxPriority;

    @Mandatory @Part
    private final Array<List<MgOperator>> operatorArray;

    @Mandatory @Part
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
            this.minPriority = minOperatorPriority;
            this.maxPriority = maxOperatorPriority;

            // create array of prioritized function lists
            this.operatorArray = new Array<>(maxOperatorPriority - minOperatorPriority + 1);
            for(int i = 0; i < this.operatorArray.count(); i++){
                this.operatorArray.set(new List<>(), i);
            }

            // fill the prioritized function list with found operator functions
            for(MgFunction function : availableOperators){
                int priority = function.getOperator().getPriority();
                this.operatorArray.get(p2i(priority)).addLast(function.getOperator());
            }
        } else {
            this.operatorArray = new Array<>();
            this.minPriority = 0;
            this.maxPriority = 0;
        }
    }

    public int getMinPriority(){
        return minPriority;
    }

    public int getMaxPriority(){
        return maxPriority;
    }

    public List<MgOperator> getOperators(int priority) {
        if(operatorArray.isEmpty()) return null;
        else return operatorArray.get(p2i(priority));
    }

    private final int p2i(int p){
        return p - minPriority;
    }

    private final int i2p(int i){
        return minPriority + i;
    }

    private static final MgOperator UNKNOWN = new MgOperator(new ReadonlyText(""));
    public MgOperator findOperator(ReadableText operatorName){
        MgOperator cachedOperator = operatorMap.get(operatorName, UNKNOWN);
        if(cachedOperator == UNKNOWN){
            for(List<MgOperator> array : operatorArray){
                for(MgOperator operator : array){
                    if(operator.getName().equals(operatorName)){
                        if(cachedOperator == UNKNOWN){
                            cachedOperator = operator;
                        } else {
                            if(!isCompatible(cachedOperator, operator)){
                                throw new LanguageException("Ambiguous operator '" + operatorName + "'.");
                            }
                        }
                    }
                }
            }
            if(cachedOperator == UNKNOWN) cachedOperator = null;
            operatorMap.set(operatorName, cachedOperator);
        }
        return cachedOperator;
    }

    private static boolean isCompatible(MgOperator first, MgOperator second){
        return first.getType() == second.getType() && first.getPriority() == second.getPriority();
    }
}
