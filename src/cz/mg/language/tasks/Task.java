package cz.mg.language.tasks;

import cz.mg.collections.list.List;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Log;


public abstract class Task {
    @Log
    private final List<LanguageException> errors = new List<>();

    public Task() {
    }

    public List<LanguageException> getErrors() {
        return errors;
    }

    protected abstract void onRun();

    public void run(){
        try {
            onRun();
        } catch (LanguageException e){
            consume(e);
            throw e;
        }
    }

    public void tryToRun(){
        try {
            onRun();
        } catch (LanguageException e){
            consume(e);
        }
    }

    private void consume(LanguageException e){
        if(!e.isConsumed()){
            e.setConsumed(true);
            errors.addLast(e);
        }
    }
}
