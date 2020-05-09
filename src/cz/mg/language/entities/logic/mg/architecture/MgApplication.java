package cz.mg.language.entities.logic.mg.architecture;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.logic.mg.schedulers.MgScheduler;


public class MgApplication extends MgArchitectureEntity {
    @Value
    private final ReadableText name;

    @Part
    private final List<MgModule> modules = new List<>();

    @Part
    private final List<MgThread> threads = new List<>();

    @Part
    private MgScheduler scheduler = null;

    public MgApplication(ReadableText name) {
        this.name = name;
    }

    @Override
    public ReadableText getName() {
        return name;
    }

    public List<MgModule> getModules() {
        return modules;
    }

    public List<MgThread> getThreads() {
        return threads;
    }

    public MgScheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(MgScheduler scheduler) {
        this.scheduler = scheduler;
    }
}
