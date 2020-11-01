package cz.mg.language.tasks.mg.resolver.command.expression.other;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.LanguageException;
import cz.mg.language.Todo;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalValueCallExpression;
import cz.mg.language.entities.mg.runtime.components.types.MgType;
import cz.mg.language.entities.mg.runtime.components.types.buildin.MgAtomType;
import cz.mg.language.entities.mg.runtime.instances.buildin.MgAtom;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgValueExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTask;
import cz.mg.language.tasks.mg.resolver.command.utilities.ExpectedParentInput;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public class MgResolveValueExpressionTask extends MgResolveExpressionTask {
    @Input
    private final MgLogicalValueCallExpression logicalExpression;

    @Output
    private MgValueExpression expression;

    public MgResolveValueExpressionTask(
        CommandContext context,
        MgLogicalValueCallExpression logicalExpression,
        ExpectedParentInput parent
    ) {
        super(context, parent);
        this.logicalExpression = logicalExpression;
    }

    @Override
    protected void onResolve() {
        expression = new MgValueExpression(createValue(getType(), logicalExpression.getValue()));
    }

    @Override
    public MgExpression getExpression() {
        return expression;
    }

    private MgAtomType getType(){
        new Todo();
        return null;
//        if(getParent() != null){
//            for(){
//
//            }
//            ReadableArray<InputConnector> remainingInputConnectors = parentInputInterface.getRemainingConnectors();
//            int count = remainingInputConnectors.count();
//            if(count >= 1){
//                InputConnector inputConnector = remainingInputConnectors.getFirst();
//                return new OutputInterface(new Array<>(new OutputConnector(createDatatype(inputConnector))));
//            } else {
//                throw new LanguageException("Cannot connect expressions. Parent has no free connectors.");
//            }
//        } else {
//            return MgTextType.getInstance();
//        }
    }

    private static MgAtom createValue(MgType type, ReadableText value){
        if(type instanceof MgAtomType){
            return ((MgAtomType) type).create(value);
        } else {
            throw new LanguageException("Expected atom type, given " + type.getName() + ".");
        }
    }
}
