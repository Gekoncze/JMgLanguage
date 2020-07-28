package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalExpressionCommand;
import cz.mg.language.tasks.mg.resolver.Context;


public class MgResolveExpressionCommandTask extends MgResolveCommandTask {
    @Input
    private final Context context;

    @Input
    private final MgLogicalExpressionCommand logicalCommand;

    @Output
    private Command command;

    public MgResolveExpressionCommandTask(Context context, MgLogicalExpressionCommand logicalCommand) {
        this.context = context;
        this.logicalCommand = logicalCommand;
    }

    @Override
    public Command getCommand() {
        return command;
    }

    @Override
    protected void onRun() {
        MgLogicalExpressionCommand cc = (MgLogicalExpressionCommand) c;
        Expr expression = resolveExpression(command.getContext(), cc.getExpression());
        noOutput(expression.getOutput());
        command.getInstructions().addCollectionLast(expression.getInstructions());
        command.getDeclaredVariables().addCollectionLast(expression.getDeclaredVariables());
    }
}
