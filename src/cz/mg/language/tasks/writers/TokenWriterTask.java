package cz.mg.language.tasks.writers;

import cz.mg.collections.list.List;
import cz.mg.language.entities.text.linear.Token;


public interface TokenWriterTask {
    List<Token> getTokens();
}
