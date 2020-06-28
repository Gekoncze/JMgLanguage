import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.entities.text.structured.parts.groups.Group;
import cz.mg.language.tasks.mg.builders.block.MgBuildRootBlockTask;
import cz.mg.language.tasks.mg.parsers.common.MgParsePageTask;
import cz.mg.language.tasks.mg.parsers.structured.MgParseBlocksTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class MgBuilderTest {
    public static void main(String[] args) {
        MgParsePageTask parsePageTask = new MgParsePageTask(load());
        parsePageTask.run();

        MgParseBlocksTask parseBlocksTask = new MgParseBlocksTask(parsePageTask.getPage());
        parseBlocksTask.run();

        printBlocks(parseBlocksTask.getRoot(), -1);

        MgBuildRootBlockTask buildRootTask = new MgBuildRootBlockTask(parseBlocksTask.getRoot());
        buildRootTask.run();
    }

    private static void printBlocks(Block block, int level){
        for(int i = 0; i < level; i++) System.out.print("    ");

        for(ReadableText stamp : block.getStamps()){
            System.out.print(stamp + " ");
        }

        for(ReadableText keyword : block.getKeywords()){
            System.out.print(keyword + " ");
        }

        for(Part part : block.getParts()){
            printPart(part);
        }

        System.out.println();

        for(Block childBlock : block.getBlocks()){
            printBlocks(childBlock, level + 1);
        }
    }

    private static void printPart(Part part){
        if(part instanceof Group){
            System.out.print(part.getClass().getSimpleName() + "[");
            for(Part childPart : ((Group) part).getParts()){
                printPart(childPart);
                System.out.print(" ");
            }
            System.out.print("]");
        } else {
            System.out.print(part.getClass().getSimpleName());
        }
    }

    private static ReadableText load(){
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(MgBuilderTest.class.getResourceAsStream("03/Test.mg")))){
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