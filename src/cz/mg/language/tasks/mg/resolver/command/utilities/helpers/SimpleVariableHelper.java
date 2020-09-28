package cz.mg.language.tasks.mg.resolver.command.utilities.helpers;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.variables.MgFunctionVariable;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.tasks.mg.resolver.command.utilities.VariableHelper;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputConnector;
import cz.mg.language.tasks.mg.resolver.command.expression.nodes.Node;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputConnector;


/*
 *  Simple implementation of variable helper interface.
 *  This implementation does not do any memory optimizations.
 *  This implementation should be used only for debug or as a fallback.
 */
public class SimpleVariableHelper implements VariableHelper {
    private static final ReadableText EXPRESSION_VARIABLE_NAME = new ReadonlyText("");

    @Mandatory @Part
    private final MgFunction function;

    public SimpleVariableHelper(MgFunction function) {
        this.function = function;
    }

    @Override
    public void sink(){
    }

    @Override
    public void raise(){
    }

    @Override
    public MgFunctionVariable nextDeclaredVariable(ReadableText name, MgDatatype datatype){
        function.getLocal().addLast(new MgFunctionVariable(name, datatype));
        function.updateVariableOffsetCache();
        return function.getLocal().getLast();
    }

    @Override
    public MgFunctionVariable nextExpressionVariable(MgDatatype datatype){
        function.getLocal().addLast(new MgFunctionVariable(EXPRESSION_VARIABLE_NAME, datatype));
        function.updateVariableOffsetCache();
        return function.getLocal().getLast();
    }

    @Override
    public MgFunctionVariable nextExpressionVariable(
        Node parent,
        InputConnector parentInputConnector,
        Node child,
        OutputConnector childOutputConnector
    ){
        return nextExpressionVariable(childOutputConnector.getRequestedDatatype());
    }
}
