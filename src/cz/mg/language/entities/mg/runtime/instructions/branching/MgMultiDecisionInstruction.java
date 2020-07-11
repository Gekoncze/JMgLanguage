package cz.mg.language.entities.mg.runtime.instructions.branching;

import cz.mg.collections.array.Array;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.architecture.MgThread;
import cz.mg.language.entities.mg.runtime.atoms.MgBoolObject;
import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;


public class MgMultiDecisionInstruction extends MgBranchingInstruction {
    @Value
    private final Array<Integer> sourceIndices;

    @Value
    private Array<MgInstruction> branches;

    @Value
    private MgInstruction defaultBranch;

    public MgMultiDecisionInstruction(Array<Integer> sourceIndices) {
        this.sourceIndices = sourceIndices;
    }

    protected Array<MgInstruction> getBranches() {
        return branches;
    }

    public void setBranches(Array<MgInstruction> branches) {
        if(this.sourceIndices.count() != branches.count()) throw new LanguageException("Index and branch count mismatch for multi-decision instruction.");
        this.branches = branches;
    }

    protected MgInstruction getDefaultBranch() {
        return defaultBranch;
    }

    public void setDefaultBranch(MgInstruction defaultBranch) {
        this.defaultBranch = defaultBranch;
    }

    @Override
    public MgInstruction run(MgThread thread) {
        for(int i = 0; i < branches.count(); i++){
            int sourceIndex = sourceIndices.get(i);
            MgBoolObject bool = (MgBoolObject) thread.getCurrentFunctionObject().getObjects().get(sourceIndex);
            if(bool.getValue()){
                return branches.get(i);
            }
        }
        return defaultBranch;
    }
}
