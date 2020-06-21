package cz.mg.language.tasks.mg.builders.block.root.command;

import cz.mg.collections.Clump;
import cz.mg.collections.list.List;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalReturnCommand;
import cz.mg.language.entities.mg.logical.parts.expressions.*;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.tasks.mg.builders.part.MgBuildExpressionPartTask;
import cz.mg.language.tasks.mg.builders.pattern.*;


public class MgBuildReturnCommandTask extends MgBuildCommandTask {
    private static final Processor PROCESSOR = new Processor<>(
        MgBuildExpressionPartTask.class,
        MgBuildReturnCommandTask.class,
        (source, destination) -> destination.command = new MgLogicalReturnCommand(source.getExpression())
    );

    private static final Clump<Pattern> PATTERNS = new List<>(
        new Pattern(
            Order.RANDOM,
            Requirement.OPTIONAL,
            Count.MULTIPLE,
            new Processor<>(
                MgBuildExpressionCommand.class,
                MgBuildReturnCommandTask.class,
                (source, destination) -> destination.getLastExpression().getArguments().addLast(source.getCommand().getExpression())
            )
        )
    );

    @Output
    private MgLogicalReturnCommand command;

    public MgBuildReturnCommandTask(Part part, Block block) {
        super(part, block);
    }

    public MgLogicalReturnCommand getCommand() {
        return command;
    }

    @Override
    protected Object getOutput() {
        return command;
    }

    @Override
    protected Processor getProcessor() {
        return PROCESSOR;
    }

    @Override
    protected Clump<Pattern> getPatterns() {
        return PATTERNS;
    }

    @Override
    protected void buildPart(Part part) {
        if(part != null){
            super.buildPart(part);
        } else {
            command = new MgLogicalReturnCommand();
        }
    }

    private MgLogicalParametrizedExpression getLastExpression() {
        MgLogicalParametrizedExpression lastExpression = getLastExpression(command.getExpression());
        if(lastExpression == null) throw new LanguageException("Cannot add child block expression.");
        return lastExpression;
    }

    private static MgLogicalParametrizedExpression getLastExpression(MgLogicalExpression expression){
        if(expression instanceof MgLogicalPathExpression){
            MgLogicalPathExpression pathExpression = (MgLogicalPathExpression) expression;
            return getLastExpression(pathExpression.getExpressions().getLast());
        }

        if(expression instanceof MgLogicalUnaryOperatorExpression){
            MgLogicalUnaryOperatorExpression unaryOperatorExpression = (MgLogicalUnaryOperatorExpression) expression;
            return getLastExpression(unaryOperatorExpression.getRight());
        }

        if(expression instanceof MgLogicalBinaryOperatorExpression){
            MgLogicalBinaryOperatorExpression binaryOperatorExpression = (MgLogicalBinaryOperatorExpression) expression;
            return getLastExpression(binaryOperatorExpression);
        }

        if(expression instanceof MgLogicalParametrizedExpression){
            return (MgLogicalParametrizedExpression) expression;
        }

        return null;
    }
}
