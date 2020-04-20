package cz.mg.compiler.entities.logic.c.elements.directives;


import cz.mg.collections.text.ReadableText;
import cz.mg.compiler.annotations.entity.Value;


public class CInclude extends CDirective {
    @Value
    private final boolean local;

    @Value
    private final ReadableText path;

    public CInclude(boolean local, ReadableText path) {
        this.local = local;
        this.path = path;
    }

    public boolean isLocal() {
        return local;
    }

    public ReadableText getPath() {
        return path;
    }
}
