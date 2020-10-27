package cz.mg.language.entities.mg.runtime.parts.expressions.operator;

import cz.mg.collections.ReadableCollection;
import cz.mg.language.LanguageException;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgOperator;
import cz.mg.language.entities.mg.runtime.parts.MgOperatorInfo;


public class MgLunaryOperatorExpression extends MgUnaryOperatorExpression {
    public MgLunaryOperatorExpression(ReadableCollection<MgOperator> operators) {
        super(checkPosition(operators));
    }

    private static ReadableCollection<MgOperator> checkPosition(ReadableCollection<MgOperator> operators){
        for(MgOperator operator: operators){
            if(operator.getInfo().getPosition() != MgOperatorInfo.Position.LEFT){
                throw new LanguageException("Illegal operator position.");
            }
        }
        return operators;
    }
}
