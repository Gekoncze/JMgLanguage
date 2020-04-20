package cz.mg.compiler.tasks.writers.c.element.statement;

import cz.mg.compiler.CompileException;
import cz.mg.compiler.entities.logic.c.elements.statements.CStatement;
import cz.mg.compiler.entities.logic.c.elements.statements.declarations.CForwardDeclaration;
import cz.mg.compiler.entities.logic.c.elements.statements.definitions.CDefinition;
import cz.mg.compiler.tasks.writers.c.element.CElementWriterTask;
import cz.mg.compiler.tasks.writers.c.element.statement.declaration.CForwardDeclarationWriterTask;
import cz.mg.compiler.tasks.writers.c.element.statement.definition.CDefinitionWriterTask;


public abstract class CStatementWriterTask extends CElementWriterTask {
    public static CStatementWriterTask create(CStatement statement){
        if(statement instanceof CForwardDeclaration) return CForwardDeclarationWriterTask.create((CForwardDeclaration) statement);
        if(statement instanceof CDefinition) return CDefinitionWriterTask.create((CDefinition)statement);
        else throw new CompileException("Could not write statement: " + statement.getClass().getSimpleName() + " is not supported.");
    }
}
