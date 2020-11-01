package cz.mg.language.entities.mg.runtime.parts.expressions.operator;

import cz.mg.collections.list.List;
import cz.mg.language.LanguageException;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgUnaryOperator;


public abstract class MgUnaryOperatorExpression extends MgOperatorExpression {
    public MgUnaryOperatorExpression(List<? extends MgReplication> replications) {
        super(replications);
    }

    public static abstract class MgReplication extends MgOperatorExpression.MgReplication {
        public MgReplication(MgUnaryOperator operator) {
            super(checkOutput(checkInput(operator)));
        }

        private static MgUnaryOperator checkInput(MgUnaryOperator operator){
            if(operator.getInputVariables().count() != 1){
                throw new LanguageException("Illegal input count for unary operator.");
            } else {
                return operator;
            }
        }

        private static MgUnaryOperator checkOutput(MgUnaryOperator operator){
            if(operator.getOutputVariables().count() != 1){
                throw new LanguageException("Illegal output count for unary operator.");
            } else {
                return operator;
            }
        }
    }
}
