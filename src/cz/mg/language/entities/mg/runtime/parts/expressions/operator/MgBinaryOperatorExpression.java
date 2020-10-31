package cz.mg.language.entities.mg.runtime.parts.expressions.operator;

import cz.mg.collections.ReadableCollection;
import cz.mg.language.LanguageException;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgBinaryOperator;


public class MgBinaryOperatorExpression extends MgOperatorExpression {
    public MgBinaryOperatorExpression(ReadableCollection<MgReplication> replications) {
        super(replications);
    }

    public static class MgReplication extends MgOperatorExpression.MgReplication {
        public MgReplication(MgBinaryOperator operator) {
            super(checkOutput(checkInput(operator)));
        }

        private static MgBinaryOperator checkInput(MgBinaryOperator operator){
            if(operator.getInputVariables().count() != 2){
                throw new LanguageException("Illegal input count for binary operator.");
            } else {
                return operator;
            }
        }

        private static MgBinaryOperator checkOutput(MgBinaryOperator operator){
            if(operator.getOutputVariables().count() != 1){
                throw new LanguageException("Illegal output count for binary operator.");
            } else {
                return operator;
            }
        }
    }
}
