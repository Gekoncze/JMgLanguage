package cz.mg.language.entities.mg.unresolved.components;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.storage.Value;
import cz.mg.language.entities.mg.unresolved.parts.MgUnresolvedDatatype;


public class MgUnresolvedVariable extends MgUnresolvedComponent {
    @Value
    private final ReadableText name;

    @Value
    private MgUnresolvedDatatype datatype;

    @Value
    private final List<ReadableText> stamps = new List<>();

    public MgUnresolvedVariable(ReadableText name, MgUnresolvedDatatype datatype) {
        this.name = name;
        this.datatype = datatype;
    }

    @Override
    public ReadableText getName() {
        return name;
    }

    public MgUnresolvedDatatype getDatatype() {
        return datatype;
    }

    public void setDatatype(MgUnresolvedDatatype datatype) {
        this.datatype = datatype;
    }

    @Override
    public List<ReadableText> getStamps() {
        return stamps;
    }
}
