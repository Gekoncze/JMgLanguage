package cz.mg.language.tasks.parsers.mg;

import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.text.common.Page;


public class MgParsePageTask extends MgParseTask {
    @Input
    private final ReadableText text;

    @Output
    private Page page = null;

    @Subtask
    private final List<MgParseLineTask> lineParserTasks = new List<>();

    public MgParsePageTask(ReadableText text) {
        this.text = text;
    }

    public Page getPage() {
        return page;
    }

    @Override
    protected void onRun() {
        page = new Page();
        Array<ReadableText> rawLines = text.splitByEach("\n");
        for(ReadableText rawLine : rawLines){
            lineParserTasks.addLast(new MgParseLineTask(rawLine));
            lineParserTasks.getLast().run();
            page.getLines().addLast(lineParserTasks.getLast().getLine());
        }
    }
}
