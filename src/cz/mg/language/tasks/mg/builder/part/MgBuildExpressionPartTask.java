package cz.mg.language.tasks.mg.builder.part;

import cz.mg.collections.list.List;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.expressions.*;
import cz.mg.language.entities.text.structured.parts.Declaration;
import cz.mg.language.entities.text.structured.parts.Pair;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.entities.text.structured.parts.groups.Colon;
import cz.mg.language.entities.text.structured.parts.groups.Group;
import cz.mg.language.entities.text.structured.parts.groups.brackets.RoundBrackets;
import cz.mg.language.entities.text.structured.parts.groups.chains.ListChain;
import cz.mg.language.entities.text.structured.parts.groups.chains.PathChain;
import cz.mg.language.entities.text.structured.parts.leaves.Name;
import cz.mg.language.entities.text.structured.parts.leaves.Signs;
import cz.mg.language.entities.text.structured.parts.leaves.Value;


public class MgBuildExpressionPartTask extends MgBuildPartTask {
    @Output
    private MgLogicalExpression expression;

    public MgBuildExpressionPartTask(Part part) {
        super(part, Part.class);
    }

    public MgLogicalExpression getExpression() {
        return expression;
    }

    @Override
    protected void buildPart(Part part) {
        expression = build(part);
    }

    private static MgLogicalExpression build(Part part){
        part = unwrapBrackets(part);

        if(part instanceof Name){
            return buildName((Name) part);
        }

        if(part instanceof Signs){
            return buildSigns((Signs) part);
        }

        if(part instanceof Value){
            return buildValue((Value) part);
        }

        if(part instanceof Declaration){
            return buildDeclaration((Declaration) part);
        }

        if(part instanceof Pair){
            return buildParametrized((Pair) part);
        }

        if(part instanceof PathChain){
            return buildPath((PathChain) part);
        }

        if(part.getClass() == Group.class){
            return buildOperator((Group) part);
        }

        throw new LanguageException("Unsupported part " + part.getClass().getSimpleName() + " in expression.");
    }

    private static MgLogicalExpression buildSigns(Signs signs) {
        return new MgLogicalSignsExpression(signs.getText());
    }

//    private static <T extends MgLogicalExpression> T build(Part part, Class<T> clazz){
//        MgLogicalExpression expression = build(part);
//        if(clazz.isInstance(expression)){
//            return (T) expression;
//        } else {
//            throw new LanguageException("Expected " + clazz.getSimpleName() + ", got " + expression.getClass().getSimpleName() + ".");
//        }
//    }

    private static MgLogicalNameExpression buildName(Name name){
        return new MgLogicalNameExpression(name.getText());
    }

    private static MgLogicalValueExpression buildValue(Value value){
        return new MgLogicalValueExpression(value.getText());
    }

    private static MgLogicalDeclarationExpression buildDeclaration(Declaration declaration){
        return new MgLogicalDeclarationExpression(
            declaration.getObjectName().getText(),
            declaration.getTypeName().getText()
        );
    }

    private static MgLogicalParametrizedExpression buildParametrized(Pair pair){
        return new MgLogicalParametrizedExpression(
            build(pair.getLeft()),
            buildGroup(unwrapColon(pair.getRight()))
        );
    }

    private static MgLogicalPathExpression buildPath(PathChain path){
        return new MgLogicalPathExpression(buildGroup(path));
    }

    private static MgLogicalOperatorExpression buildOperator(Group group){
        return new MgLogicalOperatorExpression(buildGroup(group));
    }

    private static List<MgLogicalExpression> buildGroup(Group group){
        List<MgLogicalExpression> expressions = new List<>();
        for(Part part : group.getParts()){
            expressions.addLast(build(part));
        }
        return expressions;
    }

    private static Part unwrapBrackets(Part part){
        while(part instanceof RoundBrackets){
            RoundBrackets brackets = (RoundBrackets) part;

            if(brackets.getParts().count() <= 0){
                throw new LanguageException("Empty brackets.");
            }

            if(brackets.getParts().count() > 1){
                throw new LanguageException("Illegal expression.");
            }

            part = brackets.getParts().getFirst();
        }

        return part;
    }

    private static ListChain unwrapColon(Colon colon){
        if(colon.getParts().count() <= 0){
            throw new LanguageException("Missing arguments.");
        }

        if(colon.getParts().count() > 1){
            throw new LanguageException("Illegal arguments expression.");
        }

        Part part = colon.getParts().getFirst();

        if(part instanceof Colon){
            throw new LanguageException("Multiple colons.");
        }

        if(part instanceof ListChain){
            return (ListChain) part;
        } else {
            ListChain listChain = new ListChain();
            listChain.getParts().addLast(part);
            return listChain;
        }
    }
}
