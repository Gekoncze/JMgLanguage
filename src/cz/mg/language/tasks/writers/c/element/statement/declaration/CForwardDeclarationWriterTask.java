package cz.mg.language.tasks.writers.c.element.statement.declaration;

import cz.mg.language.LanguageException;
import cz.mg.language.entities.logic.c.elements.statements.declarations.CForwardDeclaration;
import cz.mg.language.entities.logic.c.elements.statements.declarations.CFunctionForwardDeclaration;
import cz.mg.language.entities.logic.c.elements.statements.declarations.CStructureForwardDeclaration;
import cz.mg.language.tasks.writers.c.element.statement.CStatementWriterTask;


public abstract class CForwardDeclarationWriterTask extends CStatementWriterTask {
    public static CForwardDeclarationWriterTask create(CForwardDeclaration declaration){
        if(declaration instanceof CFunctionForwardDeclaration) return new CFunctionForwardDeclarationWriterTask((CFunctionForwardDeclaration) declaration);
        if(declaration instanceof CStructureForwardDeclaration) return new CStructureForwardDeclarationWriterTask((CStructureForwardDeclaration) declaration);
        throw new LanguageException("Could not write declaration: " + declaration.getClass().getSimpleName() + " is not supported.");
    }
}
