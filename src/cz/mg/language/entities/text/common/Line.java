package cz.mg.language.entities.text.common;

import cz.mg.collections.list.List;
import cz.mg.collections.text.EditableText;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;


public class Line extends CommonTextEntity {
    public static Line EMPTY_LINE = new Line();

    @Part
    private final List<Token> tokens;

    public Line() {
        tokens = new List<>();
    }

    public Line(List<Token> tokens) {
        this.tokens = tokens;
    }

    public Line(Token... tokens){
        this.tokens = new List<>(tokens);
    }

    public List<Token> getTokens() {
        return tokens;
    }

    @Override
    public ReadableText toText() {
        EditableText text = new EditableText();
        for(Token token : tokens){
            text.append(token.toText());
        }
        return text;
    }
}
