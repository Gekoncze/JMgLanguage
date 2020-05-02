package cz.mg.language.tasks.writers.c.element.directive;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.logic.c.elements.directives.CInclude;
import cz.mg.language.entities.text.common.Line;
import cz.mg.language.entities.text.common.Token;
import cz.mg.language.entities.text.c.tokens.preprocessor.CDirective;
import cz.mg.language.entities.text.c.tokens.preprocessor.CDirectiveSign;
import cz.mg.language.entities.text.common.tokens.Space;


public class CIncludeWriterTask extends CDirectiveWriterTask {
    @Input
    private final CInclude include;

    @Output
    private final List<Line> lines = new List<>();

    public CIncludeWriterTask(CInclude include) {
        this.include = include;
    }

    @Override
    public List<Line> getLines() {
        return lines;
    }

    @Override
    protected void onRun() {
        Line line = new Line();
        line.getTokens().addLast(CDirectiveSign.DIRECTIVE);
        line.getTokens().addLast(CDirective.INCLUDE);
        line.getTokens().addLast(Space.SPACE);

        if(include.isLocal()){
            line.getTokens().addLast(CDirectiveSign.INCLUDE_LOCAL_LEFT);
        } else {
            line.getTokens().addLast(CDirectiveSign.INCLUDE_STANDARD_LEFT);
        }

        line.getTokens().addLast(new Token(include.getPath()));

        if(include.isLocal()){
            line.getTokens().addLast(CDirectiveSign.INCLUDE_LOCAL_RIGHT);
        } else {
            line.getTokens().addLast(CDirectiveSign.INCLUDE_STANDARD_RIGHT);
        }

        lines.addLast(line);
    }
}
