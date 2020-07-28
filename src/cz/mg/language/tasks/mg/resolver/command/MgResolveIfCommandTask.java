package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalIfCommand;
import cz.mg.language.tasks.mg.resolver.Context;


public class MgResolveIfCommandTask extends MgResolveCommandTask {
    @Input
    private final Context context;

    @Input
    private final MgLogicalIfCommand command;

    @Output
    private Command command;

    public MgResolveIfCommandTask(Context context, MgLogicalIfCommand command) {
        this.context = context;
        this.command = command;
    }

    @Override
    public Command getCommand() {
        return command;
    }

    @Override
    protected void onRun() {
        MgLogicalIfCommand cc = (MgLogicalIfCommand) c;
        Command conditionalCommand = createNodes(command.getContext(), cc.getCommands());
        Expr expression = resolveExpression(command.getContext(), cc.getExpression());
        command.getConditions().addLast(new Condition(
            boolOutput(singleOutput(expression.getOutput())),
            conditionalCommand
        ));
        command.getInstructions().addCollectionLast(expression.getInstructions());
        command.getDeclaredVariables().addCollectionLast(expression.getDeclaredVariables());
    }
}
