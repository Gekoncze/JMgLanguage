package cz.mg.language.entities.text.linear;

import cz.mg.collections.list.List;
import cz.mg.collections.text.Text;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.text.TextEntity;


public class Page implements TextEntity {
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

    public ReadableText toText() {
        Text text = new Text();
        for(Line line : lines){
            text.append(line.toText());
            text.append("\n");
        }
        return text;
    }
}
