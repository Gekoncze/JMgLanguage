package cz.mg.language.entities.text.mg.tokens;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.LanguageException;
import cz.mg.language.entities.text.common.tokens.Bracket;


public class MgBracket extends MgSymbol implements Bracket {
    public static class Round extends MgBracket {
        public static final Round LEFT = new Round(new ReadonlyText("("));
        public static final Round RIGHT = new Round(new ReadonlyText(")"));

        private Round(ReadableText text) {
            super(text);
        }
    }

    public static class Square extends MgBracket {
        public static final Square LEFT = new Square(new ReadonlyText("["));
        public static final Square RIGHT = new Square(new ReadonlyText("]"));

        private Square(ReadableText text) {
            super(text);
        }
    }

    public static class Curly extends MgBracket {
        public static final Curly LEFT = new Curly(new ReadonlyText("{"));
        public static final Curly RIGHT = new Curly(new ReadonlyText("}"));

        private Curly(ReadableText text) {
            super(text);
        }
    }

    private MgBracket(ReadableText text) {
        super(text);
    }

    public static MgBracket parse(ReadableText text, boolean strict){
        switch (text.toString()){
            case "(": return Round.LEFT;
            case ")": return Round.RIGHT;
            case "[": return Square.LEFT;
            case "]": return Square.RIGHT;
            case "{": return Curly.LEFT;
            case "}": return Curly.RIGHT;
        }

        if(!strict){
            return null;
        } else {
            throw new LanguageException("Unsupported bracket " + text + ".");
        }
    }
}
