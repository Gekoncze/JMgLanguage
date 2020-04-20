package cz.mg.compiler.tasks.writers;

import cz.mg.collections.list.List;
import cz.mg.compiler.entities.text.Token;


public interface TokenWriterTask {
    List<Token> getTokens();
}
