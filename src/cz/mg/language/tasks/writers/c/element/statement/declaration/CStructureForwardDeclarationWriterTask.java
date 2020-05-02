package cz.mg.language.tasks.writers.c.element.statement.declaration;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.logic.c.elements.statements.declarations.CStructureForwardDeclaration;
import cz.mg.language.entities.text.c.tokens.CIdentifierToken;
import cz.mg.language.entities.text.common.Line;
import cz.mg.language.entities.text.c.tokens.CKeywordToken;
import cz.mg.language.entities.text.c.tokens.CSeparatorToken;
import cz.mg.language.entities.text.common.tokens.Space;


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
        line.getTokens().addLast(CKeywordToken.STRUCT);
        line.getTokens().addLast(Space.SPACE);
        line.getTokens().addLast(new CIdentifierToken(structureDeclaration.getName()));
        line.getTokens().addLast(CSeparatorToken.SEMICOLON);
        lines.addLast(line);
    }
}
