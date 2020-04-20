package cz.mg.compiler.entities.logic.mg.architecture;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.compiler.annotations.entity.Part;
import cz.mg.compiler.annotations.entity.Value;
import cz.mg.compiler.entities.logic.mg.MgLocable;
import cz.mg.compiler.entities.logic.mg.definitions.MgClass;
import cz.mg.compiler.entities.logic.mg.definitions.MgCollection;
import cz.mg.compiler.entities.logic.mg.definitions.MgFunction;
import cz.mg.compiler.entities.logic.mg.definitions.context.MgContext;


public class MgLocation extends MgArchitectureEntity implements MgLocable {
    @Value
    private final ReadableText name;

    @Part
    private final List<MgContext> contexts = new List<>();

    @Part
    private final List<MgCollection> collections = new List<>();

    @Part
    private final List<MgClass> classes = new List<>();

    @Part
    private final List<MgFunction> functions = new List<>();

    public MgLocation(ReadableText name) {
        this.name = name;
    }

    @Override
    public ReadableText getName() {
        return name;
    }

    public List<MgContext> getContexts() {
        return contexts;
    }

    public List<MgCollection> getCollections() {
        return collections;
    }

    public List<MgClass> getClasses() {
        return classes;
    }

    public List<MgFunction> getFunctions() {
        return functions;
    }
}
