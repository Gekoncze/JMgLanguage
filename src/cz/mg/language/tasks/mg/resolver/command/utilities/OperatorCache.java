package cz.mg.language.tasks.mg.resolver.command.utilities;

import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;
import cz.mg.collections.list.ReadableList;
import cz.mg.collections.map.Map;
import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.storage.Value;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgOperator;
import cz.mg.language.entities.mg.runtime.parts.MgOperatorInfo;
import cz.mg.language.tasks.mg.resolver.context.Context;
import cz.mg.language.tasks.mg.resolver.filter.basic.OperatorFilter;


public class OperatorCache {
    @Mandatory @Value
    private final int minPriority;

    @Mandatory @Value
    private final int maxPriority;

    @Mandatory @Part
    private final Array<List<MgOperatorInfo>> operatorArray;

    @Mandatory @Part
    private final Map<ReadableText, MgOperatorInfo> operatorMap = new Map<>();

    public OperatorCache(Context context) {
        if(context != null){
            // find all functions available in this context
            OperatorFilter filter = new OperatorFilter(context, null);
            ReadableList<MgOperator> availableOperators = filter.findAll();

            // fund the min and max priority of the available functions
            int minOperatorPriority = 0;
            int maxOperatorPriority = 0;
            for(MgOperator operator : availableOperators){
                minOperatorPriority = Math.min(minOperatorPriority, operator.getInfo().getPriority());
                maxOperatorPriority = Math.max(maxOperatorPriority, operator.getInfo().getPriority());
            }
            this.minPriority = minOperatorPriority;
            this.maxPriority = maxOperatorPriority;

            // create array of prioritized function lists
            this.operatorArray = new Array<>(maxOperatorPriority - minOperatorPriority + 1);
            for(int i = 0; i < this.operatorArray.count(); i++){
                this.operatorArray.set(new List<>(), i);
            }

            // fill the prioritized function list with found operator functions
            for(MgOperator operator : availableOperators){
                int priority = operator.getInfo().getPriority();
                this.operatorArray.get(p2i(priority)).addLast(operator.getInfo());
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

    public List<MgOperatorInfo> getOperators(int priority) {
        if(operatorArray.isEmpty()) return null;
        else return operatorArray.get(p2i(priority));
    }

    private final int p2i(int p){
        return p - minPriority;
    }

    private final int i2p(int i){
        return minPriority + i;
    }

    private static final MgOperatorInfo UNKNOWN = new MgOperatorInfo(new ReadonlyText(""), null, 0);
    public MgOperatorInfo findOperator(ReadableText operatorName){
        MgOperatorInfo cachedOperator = operatorMap.get(operatorName, UNKNOWN);
        if(cachedOperator == UNKNOWN){
            for(List<MgOperatorInfo> array : operatorArray){
                for(MgOperatorInfo operator : array){
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

    private static boolean isCompatible(MgOperatorInfo first, MgOperatorInfo second){
        return first.getPosition() == second.getPosition() && first.getPriority() == second.getPriority();
    }
}
