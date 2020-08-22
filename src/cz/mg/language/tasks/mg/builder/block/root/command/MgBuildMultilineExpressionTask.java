package cz.mg.language.tasks.mg.builder.block.root.command;

import cz.mg.collections.Clump;
import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.annotations.task.Cache;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalClumpExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalOperatorExpression;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.tasks.mg.builder.pattern.*;


public abstract class MgBuildMultilineExpressionTask extends MgBuildCommandTask {
    private static final Clump<Pattern> PATTERNS = new List<>(
        new Pattern(
            Order.RANDOM,
            Requirement.OPTIONAL,
            Count.MULTIPLE,
            new BlockProcessor<>(
                MgBuildExpressionCommand.class,
                MgBuildMultilineExpressionTask.class,
                (source, destination) -> {
                    if(destination.lineExpressions == null) destination.lineExpressions = new List<>();
                    destination.lineExpressions.addLast(source.getCommand().getExpression());
                }
            )
        )
    );

    @Cache
    private List<MgLogicalExpression> lineExpressions;

    public MgBuildMultilineExpressionTask(Block block) {
        super(block);
    }

    @Override
    protected void onRun() {
        super.onRun();
        addLineExpressions();
    }

    private void addLineExpressions(){
        if(lineExpressions != null){
            MgLogicalClumpExpression expression = (MgLogicalClumpExpression) getExpression();
            if(expression == null) setExpression(expression = new MgLogicalClumpExpression());
            expression.getExpressions().addLast(createListExpression());
        }
    }

    private MgLogicalClumpExpression createListExpression(){
        MgLogicalClumpExpression expression = new MgLogicalClumpExpression();
        for(MgLogicalExpression lineExpression : lineExpressions){
            expression.getExpressions().addLast(lineExpression);
            expression.getExpressions().addLast(new MgLogicalOperatorExpression(new ReadonlyText(",")));
        }
        expression.getExpressions().removeLast();
        return expression;
    }

    @Override
    protected Clump<Pattern> getPatterns() {
        return PATTERNS;
    }

    public abstract MgLogicalExpression getExpression();
    public abstract void setExpression(MgLogicalExpression expression);
}
