package cz.mg.language.entities.mg;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;


public class Operators {
    public static final ReadableText PLUS = new ReadonlyText("+");
    public static final ReadableText MINUS = new ReadonlyText("-");
    public static final ReadableText MULTIPLY = new ReadonlyText("*");
    public static final ReadableText DIVIDE = new ReadonlyText("/");

    public static final ReadableText EQUALS = new ReadonlyText("==");
    public static final ReadableText REFERENCE_EQUALS = new ReadonlyText("&==");
    public static final ReadableText VALUE_EQUALS = new ReadonlyText("$==");
    public static final ReadableText LESS_THAN = new ReadonlyText("<");
    public static final ReadableText LESS_THAN_OR_EQUAL = new ReadonlyText("<=");
    public static final ReadableText GREATER_THAN = new ReadonlyText(">");
    public static final ReadableText GREATER_THAN_OR_EQUAL = new ReadonlyText(">=");

    public static final ReadableText MEMBER_ACCESS = new ReadonlyText(".");
    public static final ReadableText GROUP = new ReadonlyText(",");

    public static final ReadableText ASSIGNMENT = new ReadonlyText("=");
    public static final ReadableText REFERENCE_ASSIGNMENT = new ReadonlyText("&=");
    public static final ReadableText VALUE_ASSIGNMENT = new ReadonlyText("$=");
}
