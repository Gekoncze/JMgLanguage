package cz.mg.compiler.entities.text;

import cz.mg.collections.list.List;
import cz.mg.collections.text.EditableText;
import cz.mg.collections.text.ReadableText;
import cz.mg.compiler.annotations.entity.Part;


public class Page extends TextEntity {
    @Part
    private final List<Line> lines;

    public Page() {
        lines = new List<>();
    }

    public Page(List<Line> lines) {
        this.lines = lines;
    }

    public Page(Line... lines){
        this.lines = new List<>(lines);
    }

    public List<Line> getLines() {
        return lines;
    }

    @Override
    public ReadableText toText() {
        EditableText text = new EditableText();
        for(Line line : lines){
            text.append(line.toText());
            text.append("\n");
        }
        return text;
    }
}
