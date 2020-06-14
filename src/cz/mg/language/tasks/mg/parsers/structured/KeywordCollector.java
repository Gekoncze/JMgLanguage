package cz.mg.language.tasks.mg.parsers.structured;

import cz.mg.collections.list.List;
import cz.mg.collections.map.Map;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.text.linear.tokens.KeywordToken;
import cz.mg.language.entities.text.structured.Block;


public class KeywordCollector {
    private final Map<Block, List<ReadableText>> map = new Map<>();

    public KeywordCollector() {
    }

    public void add(Block block, KeywordToken stamp){
        List<ReadableText> stamps = map.get(block);
        if(stamps == null) stamps = new List<>();
        stamps.addLast(stamp.getText());
        map.set(block, stamps);
    }

    public List<ReadableText> take(Block block){
        List<ReadableText> stamps = map.get(block);
        map.set(block, null);
        return stamps;
    }

    public int count(){
        return map.count();
    }
}
