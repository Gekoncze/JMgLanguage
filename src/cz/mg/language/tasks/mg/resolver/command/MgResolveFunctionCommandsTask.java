package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;
import cz.mg.collections.list.ListItem;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.mg.logical.components.MgLogicalFunction;
import cz.mg.language.entities.mg.logical.parts.commands.*;
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

        todo; // todo - at this point we have all nodes connected and resolved
        // todo - so we can create instructions from them
        // todo - collect all those instructions in a list
        // todo - also collect all local variables in a list

        function.setInstructions(new Array<>(instructions));
        function.setLocal(new Array<>(variables));
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
            todo;
        } else if(c instanceof MgLogicalIfCommand){
            todo;
        } else if(c instanceof MgLogicalElseIfCommand){
            todo;
        } else if(c instanceof MgLogicalElseCommand){
            todo;
        } else if(c instanceof MgLogicalWhileCommand){
            todo;
        } else if(c instanceof MgLogicalContinueCommand){
            todo;
        } else if(c instanceof MgLogicalBreakCommand){
            todo;
        } else if(c instanceof MgLogicalReturnCommand){
            todo;
        } else {
            throw new LanguageException("Unsupported command " + c.getClass().getSimpleName() + " for resolve.");
        }
    }
}
