package cz.mg.compiler.tasks.writers.c.element.statement.declaration;

import cz.mg.collections.list.List;
import cz.mg.compiler.annotations.task.Input;
import cz.mg.compiler.annotations.task.Output;
import cz.mg.compiler.entities.logic.c.elements.statements.declarations.CStructureForwardDeclaration;
import cz.mg.compiler.entities.text.Line;
import cz.mg.compiler.entities.text.tokens.c.CKeyword;
import cz.mg.compiler.entities.text.tokens.c.CSeparator;
import cz.mg.compiler.entities.text.tokens.common.Identifier;
import cz.mg.compiler.entities.text.tokens.common.Space;


public class CStructureForwardDeclarationWriterTask extends CForwardDeclarationWriterTask {
    @Input
    private final CStructureForwardDeclaration structureDeclaration;

    @Output
    private final List<Line> lines = new List<>();

    public CStructureForwardDeclarationWriterTask(CStructureForwardDeclaration structureDeclaration) {
        this.structureDeclaration = structureDeclaration;
    }

    @Override
    public List<Line> getLines() {
        return lines;
    }

    @Override
    protected void onRun() {
        Line line = new Line();
        line.getTokens().addLast(CKeyword.STRUCT);
        line.getTokens().addLast(Space.SPACE);
        line.getTokens().addLast(new Identifier(structureDeclaration.getName()));
        line.getTokens().addLast(CSeparator.SEMICOLON);
        lines.addLast(line);
    }
}
