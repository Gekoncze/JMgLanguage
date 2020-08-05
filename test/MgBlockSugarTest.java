import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.entities.text.linear.Line;
import cz.mg.language.entities.text.linear.tokens.KeywordToken;
import cz.mg.language.entities.text.linear.tokens.NameToken;
import cz.mg.language.entities.text.linear.tokens.SpaceToken;
import cz.mg.language.entities.text.linear.tokens.ValueToken;

import static cz.mg.language.tasks.mg.parser.structured.MgParseBlocksTask.splitLineByKeywords;


public class MgBlockSugarTest {
    public static void main(String[] args) {
        List<Line> lines = new List<>();

        Line line = new Line(
            new SpaceToken(new ReadonlyText(" ")),
            new SpaceToken(new ReadonlyText(" ")),
            new SpaceToken(new ReadonlyText(" ")),
            new SpaceToken(new ReadonlyText(" ")),
            new KeywordToken(new ReadonlyText("FUNCTION")),
            new SpaceToken(new ReadonlyText(" ")),
            new NameToken(new ReadonlyText("name")),
            new SpaceToken(new ReadonlyText(" ")),
            new KeywordToken(new ReadonlyText("INPUT")),
            new SpaceToken(new ReadonlyText(" ")),
            new NameToken(new ReadonlyText("foo")),
            new SpaceToken(new ReadonlyText(" ")),
            new KeywordToken(new ReadonlyText("OUTPUT")),
            new SpaceToken(new ReadonlyText(" ")),
            new NameToken(new ReadonlyText("bar")),
            new SpaceToken(new ReadonlyText(" ")),
            new KeywordToken(new ReadonlyText("LEFT")),
            new SpaceToken(new ReadonlyText(" ")),
            new KeywordToken(new ReadonlyText("OPERATOR")),
            new SpaceToken(new ReadonlyText(" ")),
            new NameToken(new ReadonlyText("not")),
            new SpaceToken(new ReadonlyText(" ")),
            new KeywordToken(new ReadonlyText("PRIORITY")),
            new SpaceToken(new ReadonlyText(" ")),
            new ValueToken(new ReadonlyText("1"))
        );

        lines.addLast(line);

        System.out.println(line.getTokens().toText("", token -> new ReadonlyText(token.getText())));
        System.out.println();

        splitLineByKeywords(lines.getFirstItem(), 1);
        for(Line newLine : lines){
            System.out.println("{LINE} " + newLine.getTokens().toText("", token -> new ReadonlyText(token.getText())));
        }
    }
}
