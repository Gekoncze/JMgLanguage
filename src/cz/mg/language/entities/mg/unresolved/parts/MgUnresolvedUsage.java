package cz.mg.language.entities.mg.unresolved.parts;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Value;


public class MgUnresolvedUsage extends MgUnresolvedPart {
    @Mandatory @Value
    private Filter filter;

    @Mandatory @Value
    private final List<ReadableText> path;

    @Optional @Value
    private ReadableText alias;

    public MgUnresolvedUsage(Filter filter, List<ReadableText> path) {
        this.filter = filter;
        this.path = path;
    }

    public Filter getFilter() {
        return filter;
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

    public enum Filter {
        ALL,
        CLASS,
        FUNCTION,
        OPERATOR,
        VARIABLE
    }
}
