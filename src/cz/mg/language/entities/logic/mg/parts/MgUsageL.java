package cz.mg.language.entities.logic.mg.parts;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.logic.mg.MgEntityL;


public class MgUsageL extends MgPartL {
    @Value
    private final List<ReadableText> path = new List<>();

    @Value
    private ReadableText alias = null;

    @Link
    private MgEntityL entity;

    public MgUsageL() {
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

    public MgEntityL getEntity() {
        return entity;
    }

    public void setEntity(MgEntityL entity) {
        this.entity = entity;
    }
}
