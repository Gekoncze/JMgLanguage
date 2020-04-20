package cz.mg.compiler.tasks.writers.c.element.statement.definition;

import cz.mg.compiler.CompileException;
import cz.mg.compiler.entities.logic.c.elements.statements.definitions.CDefinition;
import cz.mg.compiler.entities.logic.c.elements.statements.definitions.CFunctionDefinition;
import cz.mg.compiler.entities.logic.c.elements.statements.definitions.CStructureDefinition;
import cz.mg.compiler.tasks.writers.c.element.statement.CStatementWriterTask;


public abstract class CDefinitionWriterTask extends CStatementWriterTask {
    public static CDefinitionWriterTask create(CDefinition definition){
        if(definition instanceof CFunctionDefinition) return new CFunctionDefinitionWriterTask((CFunctionDefinition) definition);
        if(definition instanceof CStructureDefinition) return new CStructureDefinitionWriterTask((CStructureDefinition) definition);
        throw new CompileException("Could not write definition: " + definition.getClass().getSimpleName() + " is not supported.");
    }
}
