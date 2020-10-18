package cz.mg.language.tasks.mg.resolver.command.expression.other;

import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalValueCallExpression;
import cz.mg.language.entities.mg.runtime.components.types.MgType;
import cz.mg.language.entities.mg.runtime.components.types.buildin.MgAtomType;
import cz.mg.language.entities.mg.runtime.instances.buildin.MgAtom;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgValueExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTask;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public class MgResolveValueExpressionTask extends MgResolveExpressionTask {
    @Input
    private final MgLogicalValueCallExpression logicalExpression;

    public MgResolveValueExpressionTask(
        CommandContext context,
        MgLogicalValueCallExpression logicalExpression,
        MgResolveExpressionTask parent
    ) {
        super(context, parent);
        this.logicalExpression = logicalExpression;
    }

    @Override
    public ValueNode getNode() {
        return (ValueNode) super.getNode();
    }

    @Override
    protected Node onResolveEnter() {
        return null;
    }

    @Override
    protected void onResolveChildren() {
    }

    @Override
    protected Node onResolveLeave() {
        return new ValueNode(getParentInputConnectors(), logicalExpression.getValue());
    }

    @Override
    public MgExpression onCreateExpression() {
        return new MgValueExpression(
            getNode().getValue(),
            getOutputConnectors().getConnectors().getFirst().getConnection().getConnectionVariable()
        );
    }

    private static ReadableArray<MgOutputConnector> createOutputInterface(InputInterface parentInputInterface){
        if(parentInputInterface != null){
            ReadableArray<InputConnector> remainingInputConnectors = parentInputInterface.getRemainingConnectors();
            int count = remainingInputConnectors.count();
            if(count >= 1){
                InputConnector inputConnector = remainingInputConnectors.getFirst();
                return new OutputInterface(new Array<>(new OutputConnector(createDatatype(inputConnector))));
            } else {
                throw new LanguageException("Cannot connect expressions. Parent has no free connectors.");
            }
        } else {
            throw new LanguageException("Unknown value type.");
        }
    }

    private static MgAtom createValue(MgType type, ReadableText value){
        if(type instanceof MgAtomType){
            return ((MgAtomType) type).create(value);
        } else {
            throw new LanguageException("Expected atom type, given " + type.getName() + ".");
        }
    }
}
