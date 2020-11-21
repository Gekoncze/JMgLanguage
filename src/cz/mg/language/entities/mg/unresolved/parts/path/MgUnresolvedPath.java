package cz.mg.language.entities.mg.unresolved.parts.path;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.mg.runtime.parts.MgPart;


public abstract class MgUnresolvedPath extends MgPart {
    private final List<ReadableText> nodes = new List<>();

    public MgUnresolvedPath() {
    }

    public List<ReadableText> getNodes() {
        return nodes;
    }
}
