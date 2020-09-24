package cz.mg.language.tasks.mg.resolver;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.MgComponent;


public abstract class Context {
    @Optional @Part
    private final Context outerContext;

    public Context(@Optional Context outerContext) {
        this.outerContext = outerContext;
    }

    public @Optional Context getOuterContext() {
        return outerContext;
    }

    public abstract void forEachComponent(ComponentVisitor visitor);

    public interface ComponentVisitor {
        void onVisitComponent(MgComponent component, ReadableText alias);
    }
}
