package cz.mg.language.entities.mg.runtime.other;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.annotations.entity.Value;


public class MgOperatorR extends MgOtherR {
    public static final MgOperatorR SET_VALUE = new MgOperatorR("$=");
    public static final MgOperatorR SET_ADDRESS = new MgOperatorR("&=");
    public static final MgOperatorR EQUALS = new MgOperatorR("=");
    public static final MgOperatorR SMALLER = new MgOperatorR("<");
    public static final MgOperatorR SMALLER_OR_EQUAL = new MgOperatorR("<=");
    public static final MgOperatorR GREATER = new MgOperatorR(">");
    public static final MgOperatorR GREATER_OR_EQUAL = new MgOperatorR(">=");
    public static final MgOperatorR PLUS = new MgOperatorR("+");
    public static final MgOperatorR MINUS = new MgOperatorR("-");
    public static final MgOperatorR MULTIPLY = new MgOperatorR("*");
    public static final MgOperatorR DIVIDE = new MgOperatorR("/");
    public static final MgOperatorR DIVIDE_INTEGER = new MgOperatorR("\\");
    public static final MgOperatorR MODULO = new MgOperatorR("%");

    @Value
    private final ReadableText sign;

    private MgOperatorR(String sign) {
        this.sign = new ReadonlyText(sign);
    }

    public ReadableText getSign() {
        return sign;
    }
}
