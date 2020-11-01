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

    public abstract @Mandatory ReadableList<MgExpression> getExpressions();
    public abstract @Mandatory ReadableList<MgInputConnector> getInputConnectors();
    public abstract @Mandatory ReadableList<MgOutputConnector> getOutputConnectors();
}