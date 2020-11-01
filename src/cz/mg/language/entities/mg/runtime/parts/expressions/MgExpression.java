package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.ReadableCollection;
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

    protected abstract @Mandatory ReadableCollection<MgExpression> getExpressions();
    protected abstract @Mandatory ReadableCollection<MgInputConnector> getInputConnectors();
    protected abstract @Mandatory ReadableCollection<MgOutputConnector> getOutputConnectors();

    // hack needed because of messed up java access rights
    protected @Mandatory ReadableCollection<MgExpression> getExpressions(MgExpression expression){
        return expression.getExpressions();
    }

    // hack needed because of messed up java access rights
    protected @Mandatory ReadableCollection<MgInputConnector> getInputConnectors(MgExpression expression){
        return expression.getInputConnectors();
    }

    // hack needed because of messed up java access rights
    protected @Mandatory ReadableCollection<MgOutputConnector> getOutputConnectors(MgExpression expression){
        return expression.getOutputConnectors();
    }
}