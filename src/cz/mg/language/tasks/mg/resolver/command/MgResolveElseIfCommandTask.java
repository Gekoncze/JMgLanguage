package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalElseIfCommand;
import cz.mg.language.tasks.mg.resolver.Context;


public class MgResolveElseIfCommandTask extends MgResolveCommandTask {
    @Input
    private final Context context;

    @Input
    private final MgLogicalElseIfCommand logicalCommand;

    @Output
    private Command command;

    public MgResolveElseIfCommandTask(Context context, MgLogicalElseIfCommand logicalCommand) {
        this.context = context;
        this.logicalCommand = logicalCommand;
    }

    @Override
    public Command getCommand() {
        return command;
    }

    @Override
    protected void onRun() {
        MgLogicalElseIfCommand cc = (MgLogicalElseIfCommand) c;
        Command conditionalCommand = createNodes(command.getContext(), cc.getCommands());
        Expr expression = resolveExpression(command.getContext(), cc.getExpression());
        command.getConditions().addLast(new Condition(
            boolOutput(singleOutput(expression.getOutput())),
            conditionalCommand
        ));
        command.getInstructions().addCollectionLast(expression.getInstructions());
        command.getDeclaredVariables().addCollectionLast(expression.getDeclaredVariables());
        //todo; //todo - problem - if, else if, else can perform some wild jumps, those need to be handled properly
        // todo - the current approach is still correct, but has to handle the inner jumps at the end correctly, which can be tricky
        // todo - at some point when resolving the last command inside an if, we need to know we are in an if
        // todo - and then connect it to the correct instruction, we will need to jump at the end of if-elseif-else chain
    }
}
