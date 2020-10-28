package cz.mg.language.entities.mg.runtime.parts.expressions.operator;

import cz.mg.collections.ReadableCollection;
import cz.mg.language.LanguageException;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgOperator;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgUnaryOperator;


public abstract class MgUnaryOperatorExpression extends MgOperatorExpression {
    public MgUnaryOperatorExpression(ReadableCollection<? extends MgUnaryOperator> operators) {
        super(checkOutput(checkInput(operators)));
    }

    private static ReadableCollection<? extends MgUnaryOperator> checkInput(ReadableCollection<? extends MgUnaryOperator> operators){
        for(MgOperator operator: operators){
            if(operator.getInputVariables().count() != 1){
                throw new LanguageException("Illegal input count for unary operator.");
            }
        }
        return operators;
    }

    private static ReadableCollection<? extends MgUnaryOperator> checkOutput(ReadableCollection<? extends MgUnaryOperator> operators){
        for(MgOperator operator : operators){
            if(operator.getOutputVariables().count() != 1){
                throw new LanguageException("Illegal output count for unary operator.");
            }
        }
        return operators;
    }
}
