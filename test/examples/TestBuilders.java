package examples;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.tasks.mg.builders.block.MgBuildRootTask;
import cz.mg.language.tasks.mg.parsers.common.MgParsePageTask;
import cz.mg.language.tasks.mg.parsers.structured.MgParseBlocksTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class TestBuilders {
    public static void main(String[] args) {
        MgParsePageTask parsePageTask = new MgParsePageTask(load());
        parsePageTask.run();

        MgParseBlocksTask parseBlocksTask = new MgParseBlocksTask(parsePageTask.getPage());
        parseBlocksTask.run();

        MgBuildRootTask buildRootTask = new MgBuildRootTask(parseBlocksTask.getRoot());
        buildRootTask.run();
    }

    private static ReadableText load(){
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(TestBuilders.class.getResourceAsStream("03/Test.mg")))){
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null){
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
            return new ReadonlyText(stringBuilder.toString());
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}