package cz.mg.language.tasks.mg.parsers.structured.composers;

import cz.mg.collections.list.List;
import cz.mg.collections.list.ListItem;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.entities.text.structured.parts.groups.Operator;
import cz.mg.language.entities.text.structured.parts.leaves.special.SpecialLeaf;


public class MgComposeOperatorsTask extends MgComposeTask {
    public MgComposeOperatorsTask(List<List<Part>> groups) {
        super(groups);
    }

    @Override
    protected void onRun(ListItem<Part> partItem) {
        Part part = partItem.get();
        if(isGroupable(part) && isGroupable(partItem.getPrevious())){
            Operator operator;
            Part leftPart = partItem.removePrevious();
            if(leftPart instanceof Operator){
                operator = (Operator) leftPart;
                operator.getParts().addLast(part);
                partItem.setData(operator);
            } else {
                operator = new Operator();
                operator.getParts().addLast(leftPart);
                operator.getParts().addLast(part);
                partItem.setData(operator);
            }
        }
    }

    private boolean isGroupable(Part part){
        if(part == null) return false;
        return !(part instanceof SpecialLeaf);
    }
}
