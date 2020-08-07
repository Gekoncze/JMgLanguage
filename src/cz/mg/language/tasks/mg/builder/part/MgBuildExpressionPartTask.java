package cz.mg.language.tasks.mg.builder.part;

import cz.mg.collections.list.List;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.expressions.*;
import cz.mg.language.entities.text.structured.Part;
import cz.mg.language.entities.text.structured.parts.Group;
import cz.mg.language.entities.text.structured.parts.leaves.Name;
import cz.mg.language.entities.text.structured.parts.leaves.Operator;
import cz.mg.language.entities.text.structured.parts.leaves.Value;


public class MgBuildExpressionPartTask extends MgBuildPartTask {
    @Output
    private MgLogicalExpression expression;

    public MgBuildExpressionPartTask(List<Part> parts, MgLogicalExpression expression) {
        super(parts);
        this.expression = expression;
    }

    public MgLogicalExpression getExpression() {
        return expression;
    }

    @Override
    protected void buildParts(List<Part> parts) {
        checkNotEmpty();
        expression = buildGroup(parts);
    }

    private static MgLogicalExpression build(Part part){
        if(part instanceof Name){
            return buildName((Name) part);
        }

        if(part instanceof Operator){
            return buildOperator((Operator) part);
        }

        if(part instanceof Value){
            return buildValue((Value) part);
        }

        if(part instanceof Group){
            return buildGroup((Group) part);
        }

        throw new LanguageException("Unsupported part " + part.getClass().getSimpleName() + " in expression.");
    }

    private static MgLogicalExpression buildOperator(Operator operator) {
        return new MgLogicalOperatorExpression(operator.getText());
    }

    private static MgLogicalNameExpression buildName(Name name){
        return new MgLogicalNameExpression(name.getText());
    }

    private static MgLogicalValueExpression buildValue(Value value){
        return new MgLogicalValueExpression(value.getText());
    }

    private static MgLogicalGroupExpression buildGroup(Group group){
        return buildGroup(group.getParts());
    }

    private static MgLogicalGroupExpression buildGroup(List<Part> parts){
        List<MgLogicalExpression> expressions = new List<>();
        for(Part part : parts){
            expressions.addLast(build(part));
        }
        return new MgLogicalGroupExpression(expressions);
    }
}
