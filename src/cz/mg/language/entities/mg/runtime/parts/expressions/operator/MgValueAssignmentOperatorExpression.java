package cz.mg.language.entities.mg.runtime.parts.expressions.operator;

import cz.mg.collections.ReadableCollection;
import cz.mg.language.LanguageException;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgOperator;
import cz.mg.language.entities.mg.runtime.parts.MgOperatorInfo;


public class MgValueAssignmentOperatorExpression extends MgOperatorExpression {
    public MgValueAssignmentOperatorExpression(ReadableCollection<MgOperator> operators) {
        super(checkOutput(checkInput(checkPosition(operators))));
    }

    private static ReadableCollection<MgOperator> checkPosition(ReadableCollection<MgOperator> operators){
        for(MgOperator operator: operators){
            if(operator.getInfo().getPosition() != MgOperatorInfo.Position.BINARY){
                throw new LanguageException("Illegal operator position.");
            }
        }
        return operators;
    }

    private static ReadableCollection<MgOperator> checkInput(ReadableCollection<MgOperator> operators){
        for(MgOperator operator: operators){
            if(operator.getInputVariables().count() != 2){
                throw new LanguageException("Illegal input count for assignment operator.");
            }
        }
        return operators;
    }

    private static ReadableCollection<MgOperator> checkOutput(ReadableCollection<MgOperator> operators){
        for(MgOperator operator : operators){
            if(operator.getOutputVariables().count() != 0){
                throw new LanguageException("Illegal output count for assignment operator.");
            }
        }
        return operators;
    }
}
