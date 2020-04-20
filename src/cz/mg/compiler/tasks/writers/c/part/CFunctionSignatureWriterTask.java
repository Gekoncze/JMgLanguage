package cz.mg.compiler.tasks.writers.c.part;

import cz.mg.collections.list.List;
import cz.mg.compiler.annotations.task.Input;
import cz.mg.compiler.annotations.task.Output;
import cz.mg.compiler.annotations.task.Subtask;
import cz.mg.compiler.entities.logic.c.elements.statements.declarations.CFunctionForwardDeclaration;
import cz.mg.compiler.entities.logic.c.parts.CVariable;
import cz.mg.compiler.entities.text.Token;
import cz.mg.compiler.entities.text.tokens.c.CBracket;
import cz.mg.compiler.entities.text.tokens.c.CSeparator;
import cz.mg.compiler.entities.text.tokens.common.Identifier;
import cz.mg.compiler.entities.text.tokens.common.Space;


public class CFunctionSignatureWriterTask extends CPartWriterTask {
    @Input
    private final CFunctionForwardDeclaration functionDeclaration;

    @Output
    private final List<Token> tokens = new List<>();

    @Subtask
    private final List<CVariableWriterTask> inputWriterTasks = new List<>();

    @Subtask
    private CTypeWriterTask outputWriterTask = null;

    public CFunctionSignatureWriterTask(CFunctionForwardDeclaration functionDeclaration) {
        this.functionDeclaration = functionDeclaration;
    }

    @Override
    public List<Token> getTokens() {
        return tokens;
    }

    @Override
    protected void onRun() {
        writeOutput();
        tokens.addLast(Space.SPACE);
        writeName();
        writeInput();
    }

    private void writeName(){
        tokens.addLast(new Identifier(functionDeclaration.getName()));
    }

    private void writeOutput(){
        outputWriterTask = new CTypeWriterTask(functionDeclaration.getOutput());
        outputWriterTask.run();
        tokens.addCollectionLast(outputWriterTask.getTokens());
    }

    private void writeInput(){
        tokens.addLast(CBracket.ROUND_LEFT);

        for(CVariable input : functionDeclaration.getInput()){
            inputWriterTasks.addLast(new CVariableWriterTask(input));
            inputWriterTasks.getLast().run();
            tokens.addCollectionLast(inputWriterTasks.getLast().getTokens());
            tokens.addLast(CSeparator.COMMA);
        }
        if(!tokens.isEmpty()) if(tokens.getLast() == CSeparator.COMMA) tokens.removeLast();

        tokens.addLast(CBracket.ROUND_RIGHT);
    }
}
