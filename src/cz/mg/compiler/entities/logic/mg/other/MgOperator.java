package cz.mg.compiler.entities.logic.mg.other;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.compiler.annotations.entity.Value;


public class MgOperator extends MgOther {
    public static final MgOperator SET_VALUE = new MgOperator("$=");
    public static final MgOperator SET_ADDRESS = new MgOperator("&=");
    public static final MgOperator EQUALS = new MgOperator("=");
    public static final MgOperator SMALLER = new MgOperator("<");
    public static final MgOperator SMALLER_OR_EQUAL = new MgOperator("<=");
    public static final MgOperator GREATER = new MgOperator(">");
    public static final MgOperator GREATER_OR_EQUAL = new MgOperator(">=");
    public static final MgOperator PLUS = new MgOperator("+");
    public static final MgOperator MINUS = new MgOperator("-");
    public static final MgOperator MULTIPLY = new MgOperator("*");
    public static final MgOperator DIVIDE = new MgOperator("/");
    public static final MgOperator DIVIDE_INTEGER = new MgOperator("\\");
    public static final MgOperator MODULO = new MgOperator("%");

    @Value
    private final ReadableText sign;

    private MgOperator(String sign) {
        this.sign = new ReadonlyText(sign);
    }

    public ReadableText getSign() {
        return sign;
    }
}
