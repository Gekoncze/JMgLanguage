package cz.mg.language.entities.logic.mg.architecture;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Value;


public class MgCore extends MgArchitectureEntity implements Runnable {
    @Value
    private final ReadableText name;

    @Value
    private volatile boolean running = false;

    @Link
    private volatile MgThread thread = null;

    public MgCore(ReadableText name) {
        this.name = name;
    }

    @Override
    public ReadableText getName() {
        return name;
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
        throw new UnsupportedOperationException(); // TODO
    }
}
