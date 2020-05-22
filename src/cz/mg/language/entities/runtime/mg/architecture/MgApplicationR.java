package cz.mg.language.entities.runtime.mg.architecture;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.runtime.mg.schedulers.MgSchedulerR;


public class MgApplicationR extends MgArchitectureEntityR {
    @Value
    private final ReadableText name;

    @Part
    private final MgRootR root = new MgRootR();

    @Part
    private final List<MgModuleR> modules = new List<>();

    @Part
    private final List<MgThreadR> threads = new List<>();

    @Part
    private MgSchedulerR scheduler = null;

    public MgApplicationR(ReadableText name) {
        this.name = name;
    }

    @Override
    public ReadableText getName() {
        return name;
    }

    public List<MgModuleR> getModules() {
        return modules;
    }

    public List<MgThreadR> getThreads() {
        return threads;
    }

    public MgSchedulerR getScheduler() {
        return scheduler;
    }

    public void setScheduler(MgSchedulerR scheduler) {
        this.scheduler = scheduler;
    }
}
