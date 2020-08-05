package cz.mg.language.entities.mg.logical.parts.expressions;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.logical.parts.MgLogicalDatatype;


public class MgLogicalDeclarationExpression extends MgLogicalExpression {
    @Value
    private ReadableText name;

    @Part
    private MgLogicalDatatype datatype;

    public MgLogicalDeclarationExpression() {
    }

    public MgLogicalDeclarationExpression(ReadableText name) {
        this.name = name;
    }

    public MgLogicalDeclarationExpression(ReadableText name, MgLogicalDatatype datatype) {
        this.name = name;
        this.datatype = datatype;
    }

    public ReadableText getName() {
        return name;
    }

    public void setName(ReadableText name) {
        this.name = name;
    }

    public MgLogicalDatatype getDatatype() {
        return datatype;
    }

    public void setDatatype(MgLogicalDatatype datatype) {
        this.datatype = datatype;
    }
}
