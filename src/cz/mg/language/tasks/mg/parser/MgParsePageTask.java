package cz.mg.language.tasks.mg.parser;

import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.text.plain.Page;
import cz.mg.language.tasks.Task;


public class MgParsePageTask extends Task {
    @Input
    private final ReadableText text;

    @Output
    private Page page = null;

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
            MgParseLineTask task = new MgParseLineTask(rawLine);
            task.run();
            page.getLines().addLast(task.getLine());
        }
    }
}
