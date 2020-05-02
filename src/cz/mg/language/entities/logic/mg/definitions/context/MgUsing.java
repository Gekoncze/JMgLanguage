package cz.mg.language.entities.logic.mg.definitions.context;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.logic.mg.MgEntity;


public class MgUsing extends MgEntity {
    @Value
    private final List<ReadableText> path;

    @Value
    private final ReadableText alias;

    public MgUsing(List<ReadableText> path, ReadableText alias) {
        this.path = path;
        this.alias = alias;
    }

    public MgUsing(List<ReadableText> path) {
        this.path = path;
        this.alias = path.getLast();
    }

    public List<ReadableText> getPath() {
        return path;
    }

    public ReadableText getAlias() {
        return alias;
    }
}
