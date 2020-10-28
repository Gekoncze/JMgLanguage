package cz.mg.language.entities.mg.runtime.parts.expressions.operator;

import cz.mg.collections.ReadableCollection;
import cz.mg.language.LanguageException;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgBinaryOperator;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgOperator;


public class MgValueAssignmentOperatorExpression extends MgOperatorExpression {
    public MgValueAssignmentOperatorExpression(ReadableCollection<MgBinaryOperator> operators) {
        super(checkOutput(checkInput(operators)));
    }

    private static ReadableCollection<? extends MgOperator> checkInput(ReadableCollection<? extends MgOperator> operators){
        for(MgOperator operator: operators){
            if(operator.getInputVariables().count() != 2){
                throw new LanguageException("Illegal input count for assignment operator.");
            }
        }
        return operators;
    }

    private static ReadableCollection<? extends MgOperator> checkOutput(ReadableCollection<? extends MgOperator> operators){
        for(MgOperator operator : operators){
            if(operator.getOutputVariables().count() != 0){
                throw new LanguageException("Illegal output count for assignment operator.");
            }
        }
        return operators;
    }
}
