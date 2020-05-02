import cz.mg.collections.text.EditableText;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.text.common.Line;
import cz.mg.language.entities.text.common.Token;
import cz.mg.language.entities.text.mg.MgPage;
import cz.mg.language.tasks.parsers.MgParsePageTask;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class ParserTest {
    public static void main(String[] args) {
        MgParsePageTask parsePageTask = new MgParsePageTask(loadCode());
        parsePageTask.run();
        MgPage page = parsePageTask.getPage();

        System.out.println(loadCode());
        System.out.println();

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