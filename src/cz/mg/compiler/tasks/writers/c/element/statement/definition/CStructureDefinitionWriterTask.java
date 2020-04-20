package cz.mg.compiler.tasks.writers.c.element.statement.definition;

import cz.mg.collections.list.List;
import cz.mg.compiler.annotations.task.Input;
import cz.mg.compiler.annotations.task.Output;
import cz.mg.compiler.annotations.task.Subtask;
import cz.mg.compiler.entities.logic.c.elements.statements.definitions.CStructureDefinition;
import cz.mg.compiler.entities.logic.c.parts.CModifier;
import cz.mg.compiler.entities.text.Line;
import cz.mg.compiler.entities.text.tokens.c.CBracket;
import cz.mg.compiler.entities.text.tokens.c.CKeyword;
import cz.mg.compiler.entities.text.tokens.c.CSeparator;
import cz.mg.compiler.entities.text.tokens.common.Identifier;
import cz.mg.compiler.entities.text.tokens.common.Modifier;
import cz.mg.compiler.entities.text.tokens.common.Space;
import cz.mg.compiler.tasks.writers.c.CVariableBlockWriterTask;

import static cz.mg.compiler.tasks.writers.c.Utilities.indent;


public class CStructureDefinitionWriterTask extends CDefinitionWriterTask {
    @Input
    private final CStructureDefinition structureDefinition;

    @Output
    private final List<Line> lines = new List<>();

    @Subtask
    private CVariableBlockWriterTask variableBlockWriterTask = null;

    public CStructureDefinitionWriterTask(CStructureDefinition structureDefinition) {
        this.structureDefinition = structureDefinition;
    }

    @Override
    public List<Line> getLines() {
        return lines;
    }

    @Override
    protected void onRun() {
        writeHeader();
        writeBody();
        writeFooter();
    }

    private void writeHeader(){
        Line line = new Line();
        line.getTokens().addLast(CKeyword.STRUCT);
        line.getTokens().addLast(Space.SPACE);
        line.getTokens().addLast(new Identifier(structureDefinition.getName()));
        line.getTokens().addLast(CBracket.CURLY_LEFT);
        lines.addLast(line);
    }

    private void writeBody(){
        variableBlockWriterTask = new CVariableBlockWriterTask(structureDefinition.getVariables());
        variableBlockWriterTask.run();
        lines.addCollectionLast(indent(variableBlockWriterTask.getLines()));
    }

    private void writeFooter(){
        Line line = new Line();
        line.getTokens().addLast(CBracket.CURLY_RIGHT);
        for(CModifier modifier : structureDefinition.getModifiers()){
            line.getTokens().addLast(new Modifier(modifier.getName()));
        }
        line.getTokens().addLast(CSeparator.SEMICOLON);
        lines.addLast(line);
    }
}
