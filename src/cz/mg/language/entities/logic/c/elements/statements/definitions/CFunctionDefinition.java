package cz.mg.language.entities.logic.c.elements.statements.definitions;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.Named;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.logic.c.elements.statements.declarations.CFunctionForwardDeclaration;
import cz.mg.language.entities.logic.c.parts.CFunctionType;
import cz.mg.language.entities.logic.c.parts.CType;
import cz.mg.language.entities.logic.c.parts.CVariable;
import cz.mg.language.entities.logic.c.commands.CCommand;


public class CFunctionDefinition extends CDefinition implements Named {
    @Part
    private final CFunctionForwardDeclaration declaration;

    @Part
    private List<CCommand> commands = new List<>();

    public CFunctionDefinition(ReadableText name) {
        this(new CFunctionForwardDeclaration(name));
    }

    public CFunctionDefinition(CFunctionForwardDeclaration declaration) {
        this.declaration = declaration;
    }

    public CFunctionForwardDeclaration getDeclaration() {
        return declaration;
    }

    public CFunctionType getType(){
        return declaration.getType();
    }

    @Override
    public ReadableText getName() {
        return declaration.getName();
    }

    public List<CVariable> getInput() {
        return declaration.getInput();
    }

    public CType getOutput() {
        return declaration.getOutput();
    }

    public void setOutput(CType output) {
        this.declaration.setOutput(output);
    }

    public List<CCommand> getCommands() {
        return commands;
    }
}
