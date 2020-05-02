package cz.mg.language.tasks.writers.c.element;

import cz.mg.language.LanguageException;
import cz.mg.language.entities.logic.c.elements.CElement;
import cz.mg.language.entities.logic.c.elements.directives.CDirective;
import cz.mg.language.entities.logic.c.elements.statements.CStatement;
import cz.mg.language.tasks.writers.LineWriterTask;
import cz.mg.language.tasks.writers.c.CWriterTask;
import cz.mg.language.tasks.writers.c.element.directive.CDirectiveWriterTask;
import cz.mg.language.tasks.writers.c.element.statement.CStatementWriterTask;


public abstract class CElementWriterTask extends CWriterTask implements LineWriterTask {
    public static CElementWriterTask create(CElement element){
        if(element instanceof CDirective) return CDirectiveWriterTask.create((CDirective) element);
        if(element instanceof CStatement) return CStatementWriterTask.create((CStatement) element);
        throw new LanguageException("Could not write element: " + element.getClass().getSimpleName() + " is not supported.");
    }
}
