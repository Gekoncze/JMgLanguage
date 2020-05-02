package cz.mg.language.entities.logic.mg.definitions;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.logic.mg.MgEntity;
import cz.mg.language.entities.logic.mg.MgNamed;
import cz.mg.language.entities.logic.mg.other.MgType;


public class MgCollection extends MgClass {
    @Part
    private final List<MgParameter> parameters = new List<>();

    public MgCollection(ReadableText name) {
        super(name);
    }

    public List<MgParameter> getParameters() {
        return parameters;
    }

    public static class MgParameter extends MgEntity implements MgNamed {
        @Value
        private final ReadableText name;

        @Value
        private final MgType type;

        public MgParameter(ReadableText name, MgType type) {
            this.name = name;
            this.type = type;
        }

        @Override
        public ReadableText getName() {
            return name;
        }

        public MgType getType() {
            return type;
        }
    }
}
