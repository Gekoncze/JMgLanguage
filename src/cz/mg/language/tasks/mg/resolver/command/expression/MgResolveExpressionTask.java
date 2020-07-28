package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.language.LanguageException;
import cz.mg.language.entities.mg.logical.parts.expressions.*;
import cz.mg.language.tasks.mg.resolver.MgResolverTask;


public class MgResolveExpressionTask extends MgResolverTask {
    @Override
    protected void onRun() {
        if(expression instanceof MgLogicalValueExpression){
            //todo;
        } else if(expression instanceof MgLogicalNameExpression){
            //todo;
        } else if(expression instanceof MgLogicalDeclarationExpression){
            //todo;
        } else if(expression instanceof MgLogicalSignsExpression){
            throw new LanguageException("Unexpected sign(s) '" + ((MgLogicalSignsExpression) expression).getTarget() + "'.");
        } else if(expression instanceof MgLogicalSymbolExpression){
            throw new LanguageException("Unexpected symbol '" + ((MgLogicalSymbolExpression) expression).getSymbol() + "'.");
        } else if(expression instanceof MgLogicalOperatorExpression){
            //todo;
        } else if(expression instanceof MgLogicalParametrizedExpression){
            //todo;
        } else if(expression instanceof MgLogicalPathExpression){
            //todo;
        } else {
            throw new LanguageException("Unsupported expression " + expression.getClass().getSimpleName() + " for resolve.");
        }
        throw new RuntimeException();
    }
}
