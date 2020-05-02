package cz.mg.language.tasks.writers;

import cz.mg.collections.list.List;
import cz.mg.language.entities.text.common.Page;


public interface PageWriterTask {
    List<Page> getPages();
}
