package cz.mg.language.entities.mg.runtime.architecture;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.components.types.MgType;


public class MgCore extends MgArchitectureObject implements Runnable {
    private static final MgType TYPE = new MgType("Core");

    @Value
    private volatile boolean running = false;

    @Link
    private volatile MgThread thread = null;

    public MgCore(ReadableText name) {
        super(TYPE, name);
    }

    public synchronized boolean isRunning() {
        return running;
    }

    public synchronized void setRunning(boolean running) {
        this.running = running;
    }

    public synchronized MgThread getThread() {
        return thread;
    }

    public synchronized void setThread(MgThread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException();
    }
}
