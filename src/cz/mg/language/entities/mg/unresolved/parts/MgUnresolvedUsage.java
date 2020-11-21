package cz.mg.language.entities.mg.unresolved.parts;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.mg.unresolved.parts.path.MgUnresolvedPath;


public class MgUnresolvedUsage extends MgUnresolvedPart {
    @Mandatory @Value
    private Filter filter;

    @Mandatory @Part
    private final MgUnresolvedPath path;

    @Optional @Value
    private ReadableText alias;

    public MgUnresolvedUsage(Filter filter, MgUnresolvedPath path) {
        this.filter = filter;
        this.path = path;
    }

    public Filter getFilter() {
        return filter;
    }

    public MgUnresolvedPath getPath() {
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
        VARIABLE,
        WORKSPACE
    }
}
