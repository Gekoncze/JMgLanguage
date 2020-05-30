package cz.mg.language.tasks.builders.mg.child;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Part;


public class Children {
    @Part
    private final List<Child> children;

    public Children(Child... children) {
        this.children = new List<>(children);
    }

    public List<Child> getChildren() {
        return children;
    }
}
