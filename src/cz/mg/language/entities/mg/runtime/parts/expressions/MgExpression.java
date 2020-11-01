package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.ReadableList;
import cz.mg.language.entities.mg.runtime.MgRunnable;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;


public abstract class MgExpression implements MgRunnable {
    public MgExpression() {
    }

    public void validate(){
        for(MgExpression expression : getExpressions()){
            expression.validate();
        }

        for(MgInputConnector inputConnector : getInputConnectors()){
            inputConnector.validate();
        }

        for(MgOutputConnector outputConnector : getOutputConnectors()){
            outputConnector.validate();
        }
    }

    protected abstract @Mandatory ReadableList<MgExpression> getExpressions();
    protected abstract @Mandatory ReadableList<MgInputConnector> getInputConnectors();
    protected abstract @Mandatory ReadableList<MgOutputConnector> getOutputConnectors();

    // hack needed because of messed up java access rights
    protected static  @Mandatory ReadableList<MgExpression> getExpressions(MgExpression expression){
        return expression.getExpressions();
    }

    // hack needed because of messed up java access rights
    protected static @Mandatory ReadableList<MgInputConnector> getInputConnectors(MgExpression expression){
        return expression.getInputConnectors();
    }

    // hack needed because of messed up java access rights
    protected static @Mandatory ReadableList<MgOutputConnector> getOutputConnectors(MgExpression expression){
        return expression.getOutputConnectors();
    }
}