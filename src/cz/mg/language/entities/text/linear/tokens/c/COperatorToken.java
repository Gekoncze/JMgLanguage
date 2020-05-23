package cz.mg.language.entities.text.linear.tokens.c;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.LanguageException;
import cz.mg.language.entities.text.linear.Token;


public class COperatorToken extends Token {
    protected COperatorToken(ReadableText text) {
        super(text);
    }

    public static class Unary {
        public static COperatorToken PLUS = new COperatorToken(new ReadonlyText("+"));
        public static COperatorToken MINUS = new COperatorToken(new ReadonlyText("-"));
        public static COperatorToken INCREMENT = new COperatorToken(new ReadonlyText("++"));
        public static COperatorToken DECREMENT = new COperatorToken(new ReadonlyText("--"));
        public static COperatorToken DEREFERENCE = new COperatorToken(new ReadonlyText("*"));
        public static COperatorToken ADDRESS = new COperatorToken(new ReadonlyText("&"));

        public static COperatorToken get(ReadableText operator){
            switch (operator.toString()){
                case "+": return PLUS;
                case "-": return MINUS;
                case "++": return INCREMENT;
                case "--": return DECREMENT;
                case "*": return DEREFERENCE;
                case "&": return ADDRESS;
                default: throw new LanguageException("Operator " + operator + " is not supported.");
            }
        }
    }

    public static class Binary {
        public static COperatorToken PLUS = new COperatorToken(new ReadonlyText("+"));
        public static COperatorToken MINUS = new COperatorToken(new ReadonlyText("-"));
        public static COperatorToken MULTIPLY = new COperatorToken(new ReadonlyText("*"));
        public static COperatorToken DIVIDE = new COperatorToken(new ReadonlyText("/"));
        public static COperatorToken MODULO = new COperatorToken(new ReadonlyText("%"));
        public static COperatorToken ASSIGNMENT = new COperatorToken(new ReadonlyText("="));
        public static COperatorToken EQUAL = new COperatorToken(new ReadonlyText("=="));
        public static COperatorToken SMALLER = new COperatorToken(new ReadonlyText("<"));
        public static COperatorToken SMALLER_OR_EQUAL = new COperatorToken(new ReadonlyText("<="));
        public static COperatorToken GREATER = new COperatorToken(new ReadonlyText(">"));
        public static COperatorToken GREATER_OR_EQUAL = new COperatorToken(new ReadonlyText(">="));

        public static COperatorToken get(ReadableText operator){
            switch (operator.toString()){
                case "+": return PLUS;
                case "-": return MINUS;
                case "*": return MULTIPLY;
                case "/": return DIVIDE;
                case "%": return MODULO;
                case "=": return ASSIGNMENT;
                case "==": return EQUAL;
                case "<": return SMALLER;
                case "<=": return SMALLER_OR_EQUAL;
                case ">": return GREATER;
                case ">=": return GREATER_OR_EQUAL;
                default: throw new LanguageException("Operator " + operator + " is not supported.");
            }
        }
    }
}
