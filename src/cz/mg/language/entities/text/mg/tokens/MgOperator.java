package cz.mg.language.entities.text.mg.tokens;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.LanguageException;
import cz.mg.language.entities.text.common.tokens.Operator;


public class MgOperator extends MgSymbol implements Operator {
    public static final MgOperator PLUS = new MgOperator(new ReadonlyText("+"));
    public static final MgOperator MINUS = new MgOperator(new ReadonlyText("-"));
    public static final MgOperator MULTIPLY = new MgOperator(new ReadonlyText("*"));
    public static final MgOperator DIVIDE = new MgOperator(new ReadonlyText("/"));
    public static final MgOperator INTEGER_DIVIDE = new MgOperator(new ReadonlyText("\\"));
    public static final MgOperator MODULO = new MgOperator(new ReadonlyText("%"));
    public static final MgOperator POWER = new MgOperator(new ReadonlyText("^"));
    public static final MgOperator FACTORIAL = new MgOperator(new ReadonlyText("!"));
    public static final MgOperator SMALLER = new MgOperator(new ReadonlyText("<"));
    public static final MgOperator GREATER = new MgOperator(new ReadonlyText(">"));
    public static final MgOperator EQUAL = new MgOperator(new ReadonlyText("="));

    private MgOperator(ReadableText text) {
        super(text);
    }

    public static MgOperator parse(ReadableText text, boolean strict){
        switch (text.toString()){
            case "+": return PLUS;
            case "-": return MINUS;
            case "*": return MULTIPLY;
            case "/": return DIVIDE;
            case "\\": return INTEGER_DIVIDE;
            case "%": return MODULO;
            case "^": return POWER;
            case "!": return FACTORIAL;
            case "<": return SMALLER;
            case ">": return GREATER;
            case "=": return EQUAL;
        }

        if(!strict){
            return null;
        } else {
            throw new LanguageException("Unknown operator '" + text.toString() + "'.");
        }
    }

    public static class Extended extends MgOperator {
        public static final MgOperator VALUE_ASSIGNMENT = new MgOperator(new ReadonlyText("$="));
        public static final MgOperator OBJECT_ASSIGNMENT = new MgOperator(new ReadonlyText("&="));

        private Extended(ReadableText text) {
            super(text);
        }
    }
}
