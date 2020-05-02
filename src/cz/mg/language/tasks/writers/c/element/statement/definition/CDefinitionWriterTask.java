package cz.mg.language.tasks.writers.c.element.statement.definition;

import cz.mg.language.LanguageException;
import cz.mg.language.entities.logic.c.elements.statements.definitions.CDefinition;
import cz.mg.language.entities.logic.c.elements.statements.definitions.CFunctionDefinition;
import cz.mg.language.entities.logic.c.elements.statements.definitions.CStructureDefinition;
import cz.mg.language.tasks.writers.c.element.statement.CStatementWriterTask;


public abstract class CDefinitionWriterTask extends CStatementWriterTask {
    public static CDefinitionWriterTask create(CDefinition definition){
        if(definition instanceof CFunctionDefinition) return new CFunctionDefinitionWriterTask((CFunctionDefinition) definition);
        if(definition instanceof CStructureDefinition) return new CStructureDefinitionWriterTask((CStructureDefinition) definition);
        throw new LanguageException("Could not write definition: " + definition.getClass().getSimpleName() + " is not supported.");
    }
}
