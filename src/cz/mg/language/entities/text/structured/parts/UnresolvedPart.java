package cz.mg.language.entities.text.structured.parts;

import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.text.linear.Token;


public class UnresolvedPart extends CommonPart {
    @Value
    private final Token token;

    public UnresolvedPart(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return token;
    }
}
