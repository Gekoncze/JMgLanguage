package cz.mg.language.entities.mg.logical.parts;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.logical.MgLogicalEntity;


public class MgLogicalUsage extends MgLogicalPart {
    @Value
    private final List<ReadableText> path;

    @Value
    private ReadableText alias;

    @Link
    private MgLogicalEntity entity;

    public MgLogicalUsage() {
        this(null);
    }

    public MgLogicalUsage(List<ReadableText> path) {
        this.path = path;
    }

    public List<ReadableText> getPath() {
        return path;
    }

    public ReadableText getAlias() {
        return alias;
    }

    public void setAlias(ReadableText alias) {
        this.alias = alias;
    }

    public MgLogicalEntity getEntity() {
        return entity;
    }

    public void setEntity(MgLogicalEntity entity) {
        this.entity = entity;
    }
}
