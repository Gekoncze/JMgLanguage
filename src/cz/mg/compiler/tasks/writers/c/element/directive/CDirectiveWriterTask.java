package cz.mg.compiler.tasks.writers.c.element.directive;

import cz.mg.compiler.CompileException;
import cz.mg.compiler.entities.logic.c.elements.directives.CDirective;
import cz.mg.compiler.entities.logic.c.elements.directives.CInclude;
import cz.mg.compiler.tasks.writers.c.element.CElementWriterTask;


public abstract class CDirectiveWriterTask extends CElementWriterTask {
    public static CDirectiveWriterTask create(CDirective directive){
        if(directive instanceof CInclude) return new CIncludeWriterTask((CInclude) directive);
        throw new CompileException("Could not write include: " + directive.getClass().getSimpleName() + " is not supported.");
    }
}
