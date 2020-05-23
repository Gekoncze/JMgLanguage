package cz.mg.language.tasks.parsers.mg.common;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.text.linear.Line;
import cz.mg.language.entities.text.linear.tokens.*;
import cz.mg.language.tasks.parsers.mg.MgParseTask;


public class MgParseLineTask extends MgParseTask {
    @Input
    private final ReadableText text;

    @Output
    private Line line = null;

    private CharReader reader = null;

    public MgParseLineTask(ReadableText text) {
        this.text = text;
    }

    public Line getLine() {
        return line;
    }

    private static boolean isCommentSign(char ch){
        return ch == '#';
    }

    private static boolean isValueSign(char ch){
        return ch == '"' || ch == '\'';
    }

    private static boolean isStampSign(char ch){
        return ch == '@';
    }

    private static boolean isNumberCharacter(char ch){
        return ch >= '0' && ch <= '9';
    }

    private static boolean isLowerCharacter(char ch){
        return ch >= 'a' && ch <= 'z';
    }

    private static boolean isUpperCharacter(char ch){
        return ch >= 'A' && ch <= 'Z';
    }

    private static boolean isWordCharacter(char ch){
        return isLowerCharacter(ch) || isUpperCharacter(ch) || isNumberCharacter(ch);
    }

    private static boolean isStampNameCharacter(char ch){
        return isLowerCharacter(ch);
    }

    private static boolean isAllowedLiteralCharacter(char ch){
        return ch >= ' ' && ch <= '~';
    }

    private static boolean isAllowedCommentCharacter(char ch){
        return ch >= ' ' && ch <= '~';
    }

    private static boolean isAllowedSymbol(char ch){
        return ch >= '!' && ch <= '~';
    }

    @Override
    protected void onRun() {
        line = new Line();
        reader = new CharReader(text);

        while(reader.canRead()){
            reader.setMark();
            char ch = reader.read();
            if(ch == ' '){
                line.getTokens().addLast(new SpaceToken(reader.slice()));
            } else if(isCommentSign(ch)){
                line.getTokens().addLast(parseComment());
            } else if(isValueSign(ch)){
                line.getTokens().addLast(parseValue(ch));
            } else if(isWordCharacter(ch)){
                line.getTokens().addLast(parseWord());
            } else if(isStampSign(ch)){
                line.getTokens().addLast(parseStamp());
            } else if(isAllowedSymbol(ch)) {
                line.getTokens().addLast(new SymbolToken(reader.slice()));
            } else {
                throw new LanguageException("Illegal character " + reader.sliceChar() + " (" + (int)reader.sliceChar().get(0) + ").");
            }
        }
    }

    protected CommentToken parseComment(){
        while(reader.canRead()){
            char ch = reader.read();
            if(!isAllowedCommentCharacter(ch)) throw new LanguageException("Illegal character '" + reader.sliceChar() + "' (" + (int)ch + ") in comment.");
        }
        return new CommentToken(reader.slice(1, 0).trim());
    }

    protected ValueToken parseValue(char boundary){
        while(reader.canRead()){
            char ch = reader.read();
            if(ch == boundary){
                return new ValueToken(reader.slice());
            } else if(isAllowedLiteralCharacter(ch)) {
                continue;
            } else {
                throw new LanguageException("Illegal character " + reader.sliceChar() + " (" + (int)ch + ") in value.");
            }
        }
        throw new LanguageException("Missing closing character " + boundary + " for value.");
    }

    protected WordToken parseWord(){
        reader.back();

        boolean hasLower = false;
        boolean hasUpper = false;
        boolean hasNumber = false;

        while(reader.canRead()){
            char ch = reader.read();
            if(isLowerCharacter(ch)){
                hasLower = true;
            } else if(isUpperCharacter(ch)){
                hasUpper = true;
            } else if(isNumberCharacter(ch)){
                hasNumber = true;
            } else {
                reader.back();
                break;
            }
        }

        if(hasLower) return new NameToken(reader.slice());
        if(hasUpper) return new KeywordToken(reader.slice());
        if(hasNumber) return new NumberToken(reader.slice());

        throw new LanguageException("Unsupported word '" + reader.slice() + "'.");
    }

    protected StampToken parseStamp(){
        while(reader.canRead()){
            char ch = reader.read();
            if(!isLowerCharacter(ch)){
                reader.back();
                break;
            }
        }
        return new StampToken(reader.slice());
    }
}
