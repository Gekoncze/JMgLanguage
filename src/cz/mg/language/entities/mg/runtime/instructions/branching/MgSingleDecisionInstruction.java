package cz.mg.language.entities.mg.runtime.instructions.branching;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.architecture.MgThread;
import cz.mg.language.entities.mg.runtime.atoms.MgBoolObject;
import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;


public class MgSingleDecisionInstruction extends MgBranchingInstruction {
    @Value
    private final int sourceIndex;

    @Link
    private MgInstruction trueBranch;

    @Link
    private MgInstruction falseBranch;

    public MgSingleDecisionInstruction(int sourceIndex) {
        this.sourceIndex = sourceIndex;
    }

    protected MgInstruction getTrueBranch() {
        return trueBranch;
    }

    public void setTrueBranch(MgInstruction trueBranch) {
        this.trueBranch = trueBranch;
    }

    protected MgInstruction getFalseBranch() {
        return falseBranch;
    }

    public void setFalseBranch(MgInstruction falseBranch) {
        this.falseBranch = falseBranch;
    }

    @Override
    public MgInstruction run(MgThread thread) {
        MgBoolObject bool = (MgBoolObject) thread.getCurrentFunctionObject().getObjects().get(sourceIndex);
        if(bool.getValue()){
            return trueBranch;
        } else {
            return falseBranch;
        }
    }
}
