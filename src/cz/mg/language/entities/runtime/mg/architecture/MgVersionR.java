package cz.mg.language.entities.runtime.mg.architecture;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.runtime.mg.MgEntityR;


public class MgVersionR extends MgEntityR {
    @Value
    private final ReadableText name;

    @Value
    private final int major;

    @Value
    private final int minor;

    @Value
    private final int patch;

    public MgVersionR(ReadableText name, int major, int minor, int patch) {
        this.name = name;
        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }

    public ReadableText getName() {
        return name;
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    public int getPatch() {
        return patch;
    }

    public static boolean isCompatible(MgVersionR actualVersion, MgVersionR expectedVersion){
        if(!actualVersion.name.equals(expectedVersion.name)) return false;
        if(actualVersion.major != expectedVersion.major) return false;
        if(actualVersion.minor < expectedVersion.minor) return false;
        return true;
    }
}
