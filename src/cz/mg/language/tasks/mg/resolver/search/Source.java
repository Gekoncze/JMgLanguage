package cz.mg.language.tasks.mg.resolver.search;

import cz.mg.collections.Clump;
import cz.mg.language.entities.mg.runtime.components.MgComponent;


public interface Source {
    public Clump<? extends MgComponent> getComponents();
}
