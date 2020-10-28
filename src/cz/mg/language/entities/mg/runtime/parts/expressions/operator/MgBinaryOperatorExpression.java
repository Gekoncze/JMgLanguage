package cz.mg.language.entities.mg.runtime.parts.expressions.operator;

import cz.mg.collections.ReadableCollection;
import cz.mg.language.LanguageException;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgBinaryOperator;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgOperator;


public class MgBinaryOperatorExpression extends MgOperatorExpression {
    public MgBinaryOperatorExpression(ReadableCollection<MgBinaryOperator> operators) {
        super(checkOutput(checkInput(operators)));
    }

    private static ReadableCollection<MgBinaryOperator> checkInput(ReadableCollection<MgBinaryOperator> operators){
        for(MgOperator operator: operators){
            if(operator.getInputVariables().count() != 2){
                throw new LanguageException("Illegal input count for binary operator.");
            }
        }
        return operators;
    }

    private static ReadableCollection<MgBinaryOperator> checkOutput(ReadableCollection<MgBinaryOperator> operators){
        for(MgOperator operator : operators){
            if(operator.getOutputVariables().count() != 1){
                throw new LanguageException("Illegal output count for binary operator.");
            }
        }
        return operators;
    }
}
