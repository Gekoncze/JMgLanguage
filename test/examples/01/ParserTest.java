import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.collections.text.Text;
import cz.mg.language.entities.text.linear.Line;
import cz.mg.language.entities.text.linear.Page;
import cz.mg.language.entities.text.linear.Token;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.entities.text.structured.parts.leaves.Leaf;
import cz.mg.language.tasks.mg.parsers.structured.MgParseBlocksTask;
import cz.mg.language.tasks.mg.parsers.common.MgParsePageTask;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class ParserTest {
    public static void main(String[] args) {
        MgParsePageTask parsePageTask = new MgParsePageTask(loadCode());
        parsePageTask.run();
        Page page = parsePageTask.getPage();

        MgParseBlocksTask buildBlocksTask = new MgParseBlocksTask(page);
        buildBlocksTask.run();
        Block root = buildBlocksTask.getRoot();

        printBlocks(root, 0);
    }

    private static void printBlocks(Block block, int level){
        for(int i = 0; i < level; i++) System.out.print("    ");
        System.out.println(block.getParts().toText("[", "][", "]", (p) -> {
            String value = "";
            if(p instanceof Leaf) value = " = " + ((Leaf) p).getText().toString();
            return new ReadonlyText(p.getClass().getSimpleName() + value);
        }));
        for(Block child : block.getBlocks()){
            printBlocks(child, level + 1);
        }
    }

    private static void printPage(Page page){
        for(Line line : page.getLines()){
            for(Token token : line.getTokens()){
                System.out.println(token.getClass().getSimpleName() + ": " + token.getText());
            }
            System.out.println();
        }
    }

    private static ReadableText loadCode() {
        Text text = new Text();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("/home/me/Plocha/Dev/Java/JMgLanguage/test/examples/01/application.mg")));
            String line;
            while((line = reader.readLine()) != null){
                text.append(line);
                text.append("\n");
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return text;
    }
}