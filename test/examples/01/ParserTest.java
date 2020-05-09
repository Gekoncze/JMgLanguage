import cz.mg.collections.text.EditableText;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.text.linear.Line;
import cz.mg.language.entities.text.linear.Page;
import cz.mg.language.entities.text.linear.Token;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.tasks.parsers.mg.structured.MgParseBlocksTask;
import cz.mg.language.tasks.parsers.mg.common.MgParsePageTask;

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
        System.out.println(block.getTokens().toText("[", "][", "]"));
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
        EditableText text = new EditableText();
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