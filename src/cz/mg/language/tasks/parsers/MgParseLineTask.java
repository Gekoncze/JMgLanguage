package cz.mg.language.tasks.parsers;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.text.common.Line;
import cz.mg.language.entities.text.common.Token;
import cz.mg.language.entities.text.common.tokens.Space;
import cz.mg.language.entities.text.mg.tokens.*;


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

    private static boolean isComment(char ch){
        return ch == '#';
    }

    private static boolean isValue(char ch){
        return ch == '"' || ch == '\'';
    }

    private static boolean isWord(char ch){
        return isLowerCharacter(ch) || isUpperCharacter(ch) || isNumber(ch) || ch == '_';
    }

    private static boolean isNumber(char ch){
        return ch >= '0' && ch <= '9';
    }

    private static boolean isLowerCharacter(char ch){
        return (ch >= 'a' && ch <= 'z');
    }

    private static boolean isUpperCharacter(char ch){
        return (ch >= 'A' && ch <= 'Z');
    }

    private static boolean isAllowedLiteralCharacter(char ch){
        return (ch >= ' ' && ch <= '~');
    }

    private static boolean isAllowedCommentCharacter(char ch){
        return (ch >= ' ' && ch <= '~');
    }

    @Override
    protected void onRun() {
        line = new Line();
        reader = new CharReader(text);

        while(reader.canRead()){
            reader.setMark();
            char ch = reader.read();
            if(ch == ' '){
                line.getTokens().addLast(Space.SPACE);
            } else if(isComment(ch)){
                line.getTokens().addLast(parseComment());
            } else if(isValue(ch)){
                line.getTokens().addLast(parseValue(ch));
            } else if(isWord(ch)){
                line.getTokens().addLast(parseWord());
            } else {
                line.getTokens().addLast(parseSymbol());
            }
        }
    }

    protected MgComment parseComment(){
        while(reader.canRead()){
            char ch = reader.read();
            if(!isAllowedCommentCharacter(ch)) throw new LanguageException("Illegal character '" + reader.sliceChar() + "' (" + (int)ch + ") in comment.");
        }
        return new MgComment(reader.slice().trim());
    }

    protected MgValue parseValue(char boundary){
        while(reader.canRead()){
            char ch = reader.read();
            if(ch == boundary){
                return new MgValue(reader.slice());
            } else if(isAllowedLiteralCharacter(ch)) {
                continue;
            } else {
                throw new LanguageException("Illegal character " + reader.sliceChar() + " (" + (int)ch + ") in value.");
            }
        }
        throw new LanguageException("Missing closing character " + boundary + " for value.");
    }

    protected Token parseWord(){
        reader.back();

        boolean firstChar = true;

        boolean hasLower = false;
        boolean hasUpper = false;
        boolean hasNumber = false;
        boolean hasUpperFirst = false;
        boolean hasUnderscore = false;

        while(reader.canRead()){
            char ch = reader.read();
            if(isLowerCharacter(ch)){
                hasLower = true;
                firstChar = false;
            } else if(isUpperCharacter(ch)){
                if(firstChar) hasUpperFirst = true;
                hasUpper = true;
                firstChar = false;
            } else if(isNumber(ch)){
                hasNumber = true;
            } else if(ch == '_'){
                hasUnderscore = true;
            } else {
                reader.back();
                break;
            }
        }

        if(hasUnderscore) throw new LanguageException("Underscore in names is currently not supported.");
        if(hasLower) return new MgName(reader.slice());
        if(!hasNumber) return MgKeyword.parse(reader.slice(), true);

        throw new LanguageException("Unsupported word '" + reader.slice() + "'.");
    }

    protected Token parseSymbol(){
        Token token = null;
        if(token == null) token = MgBracket.parse(reader.sliceChar(), false);
        if(token == null) token = MgSeparator.parse(reader.sliceChar(), false);
        if(token == null) token = MgOperator.parse(reader.sliceChar(), false);
        if(token == null) token = MgModifier.parse(reader.sliceChar(), false);
        if(token == null) throw new LanguageException("Illegal character " + reader.sliceChar() + " (" + (int)reader.sliceChar().get(0) + ").");
        return token;
    }

    private static class CharReader extends ArrayReader<Character> {
        private final ReadableText text;

        public CharReader(ReadableText text) {
            this.text = text;
        }

        public ReadableText slice(){
            return text.slice(getMark(), getPosition());
        }

        public ReadableText slice(int deltaBegin, int deltaEnd){
            return text.slice(getMark() + deltaBegin, getPosition() + deltaEnd);
        }

        public ReadableText sliceChar(){
            return text.slice(getPosition()-1, getPosition());
        }

        @Override
        protected int count() {
            return text.count();
        }

        @Override
        protected Character get(int i) {
            return text.get(i);
        }

        public boolean hasNext(char ch) {
            if(!canRead()) return false;
            return text.get(getPosition() + 1) == ch;
        }

        @Override
        protected LanguageException outOfBoundsException() {
            if(getPosition() < 0) {
                return new LanguageException("Missing character at the start of line.");
            } else {
                return new LanguageException("Missing character at the end of line.");
            }
        }
    }

    private static abstract class ArrayReader<T> {
        private int position = 0;
        private int mark;

        public T read() {
            if(!canRead()) throw outOfBoundsException();
            return get(position++);
        }

        public T readOptional() {
            if(!canRead()) return null;
            return get(position++);
        }

        public void back() {
            position--;
        }

        public boolean canRead(){
            return position >= 0 && position < count();
        }

        private boolean canRead(int position){
            return position >= 0 && position < count();
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public int getMark() {
            return mark;
        }

        public void setMark() {
            mark = getPosition();
        }

        protected abstract int count();
        protected abstract T get(int i);
        protected abstract LanguageException outOfBoundsException();
    }
}
