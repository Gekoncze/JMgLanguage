package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.collections.list.List;
import cz.mg.collections.list.ListItem;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.mg.logical.components.MgLogicalFunction;
import cz.mg.language.entities.mg.logical.parts.commands.*;
import cz.mg.language.entities.mg.logical.parts.expressions.*;
import cz.mg.language.entities.mg.runtime.atoms.MgBoolObject;
import cz.mg.language.entities.mg.runtime.components.MgVariable;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.tasks.mg.resolver.Context;
import cz.mg.language.tasks.mg.resolver.MgResolverTask;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolveFunctionCommandsTask extends MgResolverTask {
    @Input
    private final Context context;

    @Input
    private final MgLogicalFunction logicalFunction;

    @Input
    private final MgFunction function;

    public MgResolveFunctionCommandsTask(Context context, MgLogicalFunction logicalFunction, MgFunction function) {
        this.context = context;
        this.logicalFunction = logicalFunction;
        this.function = function;
    }

    @Override
    protected void onRun() {
        Node node = createNodes(context, logicalFunction.getCommands());

        //todo; // todo - at this point we have all nodes connected and resolved
        // todo - so we can create instructions from them
        // todo - collect all those instructions in a list
        // todo - also collect all local variables in a list

        //function.setInstructions(new Array<>(instructions));
        //function.setLocal(new Array<>(variables));
    }

    private Node createNodes(Context outerContext, List<MgLogicalCommand> commands){
        Node node = null;
        CommandContext context = new CommandContext(outerContext);
        for(
            ListItem<MgLogicalCommand> item = commands.getLastItem();
            item != null;
            item = item.getPreviousItem()
        ){
            node = new Node(context, item.get(), node);
        }
        resolveNodes(node);
        return node;
    }

    private void resolveNodes(Node node){
        while(node != null){
            resolveNode(node);
            node = node.getNext();
        }
    }

    private void resolveNode(Node node){
        MgLogicalCommand c = node.getCommand();
        if(c instanceof MgLogicalExpressionCommand){
            MgLogicalExpressionCommand cc = (MgLogicalExpressionCommand) c;
            Expr expression = resolveExpression(node.getContext(), cc.getExpression());
            noOutput(expression.getOutput());
            node.getInstructions().addCollectionLast(expression.getInstructions());
            node.getDeclaredVariables().addCollectionLast(expression.getDeclaredVariables());
        } else if(c instanceof MgLogicalIfCommand){
            MgLogicalIfCommand cc = (MgLogicalIfCommand) c;
            Node conditionalNode = createNodes(node.getContext(), cc.getCommands());
            Expr expression = resolveExpression(node.getContext(), cc.getExpression());
            node.getConditions().addLast(new Condition(
                boolOutput(singleOutput(expression.getOutput())),
                conditionalNode
            ));
            node.getInstructions().addCollectionLast(expression.getInstructions());
            node.getDeclaredVariables().addCollectionLast(expression.getDeclaredVariables());
        } else if(c instanceof MgLogicalElseIfCommand){
            MgLogicalElseIfCommand cc = (MgLogicalElseIfCommand) c;
            Node conditionalNode = createNodes(node.getContext(), cc.getCommands());
            Expr expression = resolveExpression(node.getContext(), cc.getExpression());
            node.getConditions().addLast(new Condition(
                boolOutput(singleOutput(expression.getOutput())),
                conditionalNode
            ));
            node.getInstructions().addCollectionLast(expression.getInstructions());
            node.getDeclaredVariables().addCollectionLast(expression.getDeclaredVariables());
            //todo; //todo - problem - if, else if, else can perform some wild jumps, those need to be handled properly
            // todo - the current approach is still correct, but has to handle the inner jumps at the end correctly, which can be tricky
            // todo - at some point when resolving the last command inside an if, we need to know we are in an if
            // todo - and then connect it to the correct instruction, we will need to jump at the end of if-elseif-else chain
        } else if(c instanceof MgLogicalElseCommand){
            //todo;
        } else if(c instanceof MgLogicalWhileCommand){
            //todo;
        } else if(c instanceof MgLogicalContinueCommand){
            //todo;
        } else if(c instanceof MgLogicalBreakCommand){
            //todo;
        } else if(c instanceof MgLogicalReturnCommand){
            //todo;
        } else {
            throw new LanguageException("Unsupported command " + c.getClass().getSimpleName() + " for resolve.");
        }

        // todo - we might need to connect last instruction to one of the next node instructions here somehow ?
    }

    private Expr resolveExpression(Context context, MgLogicalExpression expression){
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

    private MgVariable singleOutput(List<MgVariable> output){
        if(output.count() <= 0){
            throw new LanguageException("Expression has no output value.");
        } else if(output.count() == 1){
            return output.getFirst();
        } else {
            throw new LanguageException("Expression has multiple output values.");
        }
    }

    private MgVariable boolOutput(MgVariable variable){
        if(variable.getDatatype().getType() == MgBoolObject.TYPE){
            return variable;
        } else {
            throw new LanguageException("Expected " + MgBoolObject.TYPE.getName() + " output (buildin type), got " + variable.getDatatype().getType().getName());
        }
    }

    private void noOutput(List<MgVariable> output){
        if(output.count() > 0){
            throw new LanguageException("Ignored expression output.");
        }
    }
}
