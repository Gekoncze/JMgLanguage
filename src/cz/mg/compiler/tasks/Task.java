package cz.mg.compiler.tasks;

import cz.mg.collections.list.List;
import cz.mg.compiler.CompileException;
import cz.mg.compiler.annotations.task.Log;


public abstract class Task {
    @Log
    private final List<CompileException> errors = new List<>();

    public Task() {
    }

    public List<CompileException> getErrors() {
        return errors;
    }

    protected abstract void onRun();

    public void run(){
        try {
            onRun();
        } catch (CompileException e){
            consume(e);
            throw e;
        }
    }

    public void tryToRun(){
        try {
            onRun();
        } catch (CompileException e){
            consume(e);
        }
    }

    private void consume(CompileException e){
        if(!e.isConsumed()){
            e.setConsumed(true);
            errors.addLast(e);
        }
    }
}
