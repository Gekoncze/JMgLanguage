package cz.mg.language.entities.text.mg.tokens;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.text.common.Token;
import cz.mg.language.entities.text.common.tokens.Comment;


public class MgComment extends Token implements Comment {
    public MgComment(ReadableText text) {
        super(text);
    }
}
