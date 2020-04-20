package cz.mg.compiler.tasks.writers.c.element.statement.declaration;

import cz.mg.compiler.CompileException;
import cz.mg.compiler.entities.logic.c.elements.statements.declarations.CForwardDeclaration;
import cz.mg.compiler.entities.logic.c.elements.statements.declarations.CFunctionForwardDeclaration;
import cz.mg.compiler.entities.logic.c.elements.statements.declarations.CStructureForwardDeclaration;
import cz.mg.compiler.tasks.writers.c.element.statement.CStatementWriterTask;


public abstract class CForwardDeclarationWriterTask extends CStatementWriterTask {
    public static CForwardDeclarationWriterTask create(CForwardDeclaration declaration){
        if(declaration instanceof CFunctionForwardDeclaration) return new CFunctionForwardDeclarationWriterTask((CFunctionForwardDeclaration) declaration);
        if(declaration instanceof CStructureForwardDeclaration) return new CStructureForwardDeclarationWriterTask((CStructureForwardDeclaration) declaration);
        throw new CompileException("Could not write declaration: " + declaration.getClass().getSimpleName() + " is not supported.");
    }
}
