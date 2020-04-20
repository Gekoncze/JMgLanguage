package cz.mg.compiler.tasks.writers.c.part;

import cz.mg.collections.Collection;
import cz.mg.collections.list.List;
import cz.mg.compiler.annotations.task.Input;
import cz.mg.compiler.annotations.task.Output;
import cz.mg.compiler.entities.logic.c.parts.CModifier;
import cz.mg.compiler.entities.logic.c.parts.CPointer;
import cz.mg.compiler.entities.logic.c.parts.CType;
import cz.mg.compiler.entities.text.Token;
import cz.mg.compiler.entities.text.tokens.c.CTypeModifier;
import cz.mg.compiler.entities.text.tokens.common.Identifier;
import cz.mg.compiler.entities.text.tokens.common.Modifier;


public class CTypeWriterTask extends CPartWriterTask {
    @Input
    private final CType type;

    @Output
    private final List<Token> tokens = new List<>();

    public CTypeWriterTask(CType type) {
        this.type = type;
    }

    @Override
    public List<Token> getTokens() {
        return tokens;
    }

    @Override
    protected void onRun() {
        writeModifiers(type.getModifiers());
        tokens.addLast(new Identifier(type.getName()));
        writeTypePointers();
    }

    private void writeModifiers(Collection<CModifier> modifiers){
        for(CModifier modifier : modifiers){
            tokens.addLast(new Modifier(modifier.getName()));
        }
    }

    private void writeTypePointers(){
        for(CPointer pointer : type.getPointers()){
            tokens.addLast(CTypeModifier.POINTER);
            if(pointer.getModifers().count() > 0){
                writeModifiers(pointer.getModifers());
            }
        }
    }
}
