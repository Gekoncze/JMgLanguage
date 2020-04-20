package cz.mg.compiler.entities.text.tokens.c;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.compiler.CompileException;
import cz.mg.compiler.entities.text.tokens.common.Operator;


public class COperator extends Operator {
    protected COperator(ReadableText text) {
        super(text);
    }

    public static class Unary {
        public static COperator PLUS = new COperator(new ReadonlyText("+"));
        public static COperator MINUS = new COperator(new ReadonlyText("-"));
        public static COperator INCREMENT = new COperator(new ReadonlyText("++"));
        public static COperator DECREMENT = new COperator(new ReadonlyText("--"));
        public static COperator DEREFERENCE = new COperator(new ReadonlyText("*"));
        public static COperator ADDRESS = new COperator(new ReadonlyText("&"));

        public static COperator get(ReadableText operator){
            switch (operator.toString()){
                case "+": return PLUS;
                case "-": return MINUS;
                case "++": return INCREMENT;
                case "--": return DECREMENT;
                case "*": return DEREFERENCE;
                case "&": return ADDRESS;
                default: throw new CompileException("Operator " + operator + " is not supported.");
            }
        }
    }

    public static class Binary {
        public static COperator PLUS = new COperator(new ReadonlyText("+"));
        public static COperator MINUS = new COperator(new ReadonlyText("-"));
        public static COperator MULTIPLY = new COperator(new ReadonlyText("*"));
        public static COperator DIVIDE = new COperator(new ReadonlyText("/"));
        public static COperator MODULO = new COperator(new ReadonlyText("%"));
        public static COperator ASSIGNMENT = new COperator(new ReadonlyText("="));
        public static COperator EQUAL = new COperator(new ReadonlyText("=="));
        public static COperator SMALLER = new COperator(new ReadonlyText("<"));
        public static COperator SMALLER_OR_EQUAL = new COperator(new ReadonlyText("<="));
        public static COperator GREATER = new COperator(new ReadonlyText(">"));
        public static COperator GREATER_OR_EQUAL = new COperator(new ReadonlyText(">="));

        public static COperator get(ReadableText operator){
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
                default: throw new CompileException("Operator " + operator + " is not supported.");
            }
        }
    }
}
