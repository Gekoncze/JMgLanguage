package cz.mg.language.entities.mg.runtime.parts.expressions.assignment;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.entities.mg.runtime.MgRunnable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public class MgAssignmentExpression extends MgExpression {
    @Mandatory @Part
    protected final MgExpression sourceExpression;
    
    @Mandatory @Part
    private final List<Replication> replications = new List<>();

    public MgAssignmentExpression(MgExpression sourceExpression, List<? extends Replication> replications) {
        this.sourceExpression = sourceExpression;
        this.replications.addCollectionLast(replications);
    }

    public MgExpression getSourceExpression() {
        return sourceExpression;
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        sourceExpression.run(functionInstance);
        for(Replication replication : replications){
            replication.run(functionInstance);
        }
    }

    public static abstract class Replication implements MgRunnable {
        public Replication() {
        }
    }
}
