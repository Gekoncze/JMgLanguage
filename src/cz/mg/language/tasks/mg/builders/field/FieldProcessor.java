package cz.mg.language.tasks.mg.builders.field;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.tasks.mg.builders.MgBuildTask;
import java.lang.reflect.Constructor;


public class FieldProcessor<Source extends MgBuildTask, Destination extends MgBuildTask> {
    @Part
    private final Constructor<? extends Source> factory;

    @Part
    private final FieldSetter<Source, Destination> setter;

    public FieldProcessor(Class<? extends Source> source, Class<? extends Destination> destination, FieldSetter<Source, Destination> setter) {
        try {
            this.factory = source.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        this.setter = setter;
    }

    public Constructor<? extends Source> getFactory() {
        return factory;
    }

    public FieldSetter<Source, Destination> getSetter() {
        return setter;
    }
}
