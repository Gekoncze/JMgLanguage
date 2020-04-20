package cz.mg.compiler.tasks.writers;

import cz.mg.collections.list.List;
import cz.mg.compiler.entities.text.Page;


public interface PageWriterTask {
    List<Page> getPages();
}
