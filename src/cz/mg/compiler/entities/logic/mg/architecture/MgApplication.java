package cz.mg.compiler.entities.logic.mg.architecture;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.compiler.annotations.entity.Part;
import cz.mg.compiler.annotations.entity.Value;
import cz.mg.compiler.entities.logic.mg.MgNamed;
import cz.mg.compiler.entities.logic.mg.schedulers.MgScheduler;


public class MgApplication extends MgArchitectureEntity implements MgNamed {
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
