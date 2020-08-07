package cz.mg.language.entities.mg.runtime.parts;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Value;


public class MgVersion extends MgPart {
    @Value
    private final ReadableText name;

    @Value
    private final int major;

    @Value
    private final int minor;

    @Value
    private final int patch;

    public MgVersion(ReadableText name, int major, int minor, int patch) {
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

    public static boolean isCompatible(MgVersion actualVersion, MgVersion expectedVersion){
        if(!actualVersion.name.equals(expectedVersion.name)) return false;
        if(actualVersion.major != expectedVersion.major) return false;
        if(actualVersion.minor < expectedVersion.minor) return false;
        return true;
    }
}
