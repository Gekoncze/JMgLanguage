package cz.mg.language.entities.mg.unresolved.components;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.LanguageException;
import cz.mg.language.entities.mg.unresolved.parts.MgUnresolvedUsage;


public class MgUnresolvedWorkspace extends MgUnresolvedComponent {
    @Mandatory @Part
    private final List<@Mandatory @Part MgUnresolvedUsage> usages = new List<>();

    public MgUnresolvedWorkspace() {
        super(new ReadonlyText("ANONYMOUS"));
    }

    public MgUnresolvedWorkspace(ReadableText name) {
        super(name);
    }

    @Override
    public MgUnresolvedWorkspace getWorkspace() {
        throw new LanguageException("Usages outside of workspace definition are not allowed.");
    }

    @Override
    public void setWorkspace(MgUnresolvedWorkspace workspace) {
        throw new LanguageException("Usages outside of workspace definition are not allowed.");
    }

    public List<MgUnresolvedUsage> getUsages() {
        return usages;
    }
}
