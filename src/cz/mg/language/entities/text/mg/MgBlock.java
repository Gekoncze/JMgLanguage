package cz.mg.language.entities.text.mg;

import cz.mg.collections.list.List;
import cz.mg.collections.text.EditableText;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.entities.text.TextEntity;
import cz.mg.language.entities.text.common.Line;


public class MgBlock extends TextEntity {
    @Link
    private final Line line;

    @Link
    private final List<MgBlock> blocks = new List<>();

    public MgBlock(Line line) {
        this.line = line;
    }

    public Line getLine() {
        return line;
    }

    @Override
    public ReadableText toText() {
        EditableText text = new EditableText(line.toText());
        text.append("\n");
        text.append(blocks.toText("\n", block -> block.line.toText()));
        return text;
    }
}
