package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.list.ReadableList;
import cz.mg.language.LanguageException;
import cz.mg.language.entities.mg.logical.parts.expressions.*;
import cz.mg.language.entities.mg.runtime.components.MgVariable;
import cz.mg.language.entities.mg.runtime.other.MgDatatype;
import cz.mg.language.tasks.mg.resolver.MgResolverTask;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public abstract class MgResolveExpressionTask extends MgResolverTask {
    public static MgResolveExpressionTask create(CommandContext context, MgLogicalExpression logicalExpression){
        return create(context, logicalExpression, null, false);
    }

    public static MgResolveExpressionTask create(CommandContext context, MgLogicalExpression logicalExpression, Expression parent){
        return create(context, logicalExpression, parent, false);
    }

    public static MgResolveExpressionTask create(CommandContext context, MgLogicalExpression logicalExpression, boolean optional){
        return create(context, logicalExpression, null, optional);
    }

    public static MgResolveExpressionTask create(CommandContext context, MgLogicalExpression logicalExpression, Expression parent, boolean optional){
        if(logicalExpression instanceof MgLogicalValueExpression){
            return new MgResolveValueExpressionTask(context, (MgLogicalValueExpression) logicalExpression, parent);
        }

        if(logicalExpression instanceof MgLogicalNameExpression) {
            return new MgResolveNameExpressionTask(context, (MgLogicalNameExpression) logicalExpression, parent);
        }

        if(logicalExpression instanceof MgLogicalDeclarationExpression){
            return new MgResolveDeclarationExpressionTask(context, (MgLogicalDeclarationExpression) logicalExpression, parent);
        }

        if(logicalExpression instanceof MgLogicalSignsExpression){
            if(optional) return null;
            throw new LanguageException("Unexpected expression " + logicalExpression.getClass().getSimpleName() + " '" + ((MgLogicalSignsExpression) logicalExpression).getTarget() + "' for resolve.");
        }

        if(logicalExpression instanceof MgLogicalSymbolExpression){
            if(optional) return null;
            throw new LanguageException("Unexpected expression " + logicalExpression.getClass().getSimpleName() + " '" + ((MgLogicalSymbolExpression) logicalExpression).getSymbol() + "' for resolve.");
        }

        if(logicalExpression instanceof MgLogicalOperatorExpression){
            return new MgResolveOperatorExpressionTask(context, (MgLogicalOperatorExpression) logicalExpression, parent);
        }

        if(logicalExpression instanceof MgLogicalParametrizedExpression){
            return new MgResolveParametrizedExpressionTask(context, (MgLogicalParametrizedExpression) logicalExpression, parent);
        }

        if(logicalExpression instanceof MgLogicalPathExpression){
            return new MgResolvePathExpressionTask(context, (MgLogicalPathExpression) logicalExpression, parent);
        }

        if(optional) return null;
        throw new LanguageException("Unsupported expression " + logicalExpression.getClass().getSimpleName() + " for resolve.");
    }

    public MgResolveExpressionTask() {
    }

    public abstract Expression getExpression();

    protected ReadableArray<MgVariable> io(){
        return new Array<>();
    }

    protected ReadableArray<MgVariable> io(MgVariable... io){
        return new Array<>(io);
    }

    protected ReadableArray<MgVariable> io(ReadableArray<MgVariable> io){
        return io;
    }

    protected ReadableArray<MgVariable> io(ReadableList<MgVariable> io){
        return new Array(io);
    }

    protected void match(Expression parent, Expression child){
        if(!Matcher.matches(parent, child)){
            throw new LanguageException("Type mismatch.");
        }
    }
}
