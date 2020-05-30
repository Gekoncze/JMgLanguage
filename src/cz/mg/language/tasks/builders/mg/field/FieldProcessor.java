package cz.mg.language.tasks.builders.mg.field;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.tasks.builders.mg.MgBuildTask;

import java.lang.reflect.Constructor;


public class FieldProcessor {
    @Part
    private final Constructor<? extends MgBuildTask> factory;

    @Part
    private final FieldSetter setter;

    public FieldProcessor(Class<? extends MgBuildTask> builder, FieldSetter setter) {
        try {
            this.factory = builder.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        this.setter = setter;
    }

    public Constructor<? extends MgBuildTask> getFactory() {
        return factory;
    }

    public FieldSetter getSetter() {
        return setter;
    }
}
