package cz.mg.compiler.tasks.writers;

import cz.mg.collections.list.List;
import cz.mg.compiler.entities.text.Line;


public interface LineWriterTask {
    List<Line> getLines();
}
