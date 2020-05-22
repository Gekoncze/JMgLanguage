package cz.mg.language.entities.runtime.mg.components;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.Named;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.runtime.mg.MgEntityR;
import cz.mg.language.entities.runtime.mg.other.MgTypeR;


public class MgCollectionR extends MgClassR {
    @Part
    private final List<MgParameterR> parameters = new List<>();

    public MgCollectionR(ReadableText name) {
        super(name);
    }

    public List<MgParameterR> getParameters() {
        return parameters;
    }

    public static class MgParameterR extends MgEntityR implements Named {
        @Value
        private final ReadableText name;

        @Value
        private final MgTypeR type;

        public MgParameterR(ReadableText name, MgTypeR type) {
            this.name = name;
            this.type = type;
        }

        @Override
        public ReadableText getName() {
            return name;
        }

        public MgTypeR getType() {
            return type;
        }
    }
}
