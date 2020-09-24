package cz.mg.language.entities.mg.logical.components;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.storage.Value;
import cz.mg.language.entities.mg.logical.parts.MgLogicalDatatype;


public class MgLogicalVariable extends MgLogicalComponent {
    @Value
    private final ReadableText name;

    @Value
    private MgLogicalDatatype datatype;

    @Value
    private final List<ReadableText> stamps = new List<>();

    public MgLogicalVariable(ReadableText name, MgLogicalDatatype datatype) {
        this.name = name;
        this.datatype = datatype;
    }

    @Override
    public ReadableText getName() {
        return name;
    }

    public MgLogicalDatatype getDatatype() {
        return datatype;
    }

    public void setDatatype(MgLogicalDatatype datatype) {
        this.datatype = datatype;
    }

    @Override
    public List<ReadableText> getStamps() {
        return stamps;
    }
}
