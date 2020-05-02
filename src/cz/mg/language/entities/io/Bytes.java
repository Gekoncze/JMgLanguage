package cz.mg.language.entities.io;

import cz.mg.language.annotations.entity.Value;
import java.nio.ByteBuffer;


public class Bytes extends IOEntity {
    @Value
    private final ByteBuffer byteBuffer;

    public Bytes(ByteBuffer byteBuffer) {
        this.byteBuffer = byteBuffer;
    }

    public ByteBuffer getByteBuffer() {
        return byteBuffer;
    }
}
