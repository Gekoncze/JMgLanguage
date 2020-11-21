package cz.mg.language.entities.mg.runtime.components.variables;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Cache;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.types.MgStructuredType;


public class MgInstanceVariable extends MgVariable {
    @Mandatory @Link
    private final MgStructuredType parent;

    @Optional @Cache
    private MgCache cache;

    public MgInstanceVariable(ReadableText name, MgStructuredType parent) {
        super(name);
        this.parent = parent;
    }

    public MgStructuredType getParent() {
        return parent;
    }

    public MgCache getCache() {
        if(cache == null) cache = new MgCache(this);
        return cache;
    }

    public static class MgCache {
        @Mandatory @Value
        private final int offset;

        public MgCache(MgInstanceVariable variable) {
            this.offset = variable.getParent().getInstanceVariables().indexOf(variable);
        }

        public int getOffset() {
            return offset;
        }
    }
}
