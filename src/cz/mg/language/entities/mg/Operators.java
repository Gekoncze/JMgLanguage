package cz.mg.language.entities.mg;

import cz.mg.collections.map.Map;
import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;


public class Operators {
    public static final ReadableText PLUS = new ReadonlyText("+");
    public static final ReadableText MINUS = new ReadonlyText("-");
    public static final ReadableText MULTIPLY = new ReadonlyText("*");
    public static final ReadableText DIVIDE = new ReadonlyText("/");

    public static final ReadableText POWER = new ReadonlyText("^");

    public static final ReadableText EQUALS = new ReadonlyText("==");
    public static final ReadableText REFERENCE_EQUALS = new ReadonlyText("&==");
    public static final ReadableText VALUE_EQUALS = new ReadonlyText("$==");
    public static final ReadableText LESS_THAN = new ReadonlyText("<");
    public static final ReadableText LESS_THAN_OR_EQUAL = new ReadonlyText("<=");
    public static final ReadableText GREATER_THAN = new ReadonlyText(">");
    public static final ReadableText GREATER_THAN_OR_EQUAL = new ReadonlyText(">=");

    public static final ReadableText MEMBER_ACCESS = new ReadonlyText("."); // always 2nd highest priority, 1st is function call
    public static final ReadableText GROUP = new ReadonlyText(","); // always smallest priority

    public static final ReadableText ASSIGNMENT = new ReadonlyText("=");
    public static final ReadableText REFERENCE_ASSIGNMENT = new ReadonlyText("&=");
    public static final ReadableText VALUE_ASSIGNMENT = new ReadonlyText("$=");

    public static final Map<ReadableText, Integer> PRIORITIES = new Map<>(
        new Map.Pair<>(REFERENCE_ASSIGNMENT, 1),
        new Map.Pair<>(VALUE_ASSIGNMENT, 1),

        new Map.Pair<>(EQUALS, 2),
        new Map.Pair<>(REFERENCE_EQUALS, 2),
        new Map.Pair<>(VALUE_EQUALS, 2),
        new Map.Pair<>(LESS_THAN, 2),
        new Map.Pair<>(LESS_THAN_OR_EQUAL, 2),
        new Map.Pair<>(GREATER_THAN, 2),
        new Map.Pair<>(GREATER_THAN_OR_EQUAL, 2),

        new Map.Pair<>(PLUS, 3),
        new Map.Pair<>(MINUS, 3),

        new Map.Pair<>(MULTIPLY, 4),
        new Map.Pair<>(DIVIDE, 4),

        new Map.Pair<>(POWER, 5)
    );
}
