package cz.mg.language.tasks.mg.resolver.contexts.utilities;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.storage.Value;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.MgComponent;


public class Usage {
    @Mandatory @Link
    private final MgComponent component;

    @Optional @Value
    private final ReadableText alias;

    public Usage(@Link MgComponent component, ReadableText alias) {
        this.component = component;
        this.alias = alias;
    }

    public MgComponent getComponent() {
        return component;
    }

    public ReadableText getAlias() {
        return alias;
    }

    public ReadableText getName() {
        if(alias != null){
            return alias;
        } else {
            return component.getName();
        }
    }
}
