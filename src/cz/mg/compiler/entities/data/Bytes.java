package cz.mg.compiler.entities.data;

import cz.mg.compiler.annotations.entity.Value;
import java.nio.ByteBuffer;


public class Bytes extends DataEntity {
    @Value
    private final ByteBuffer byteBuffer;

    public Bytes(ByteBuffer byteBuffer) {
        this.byteBuffer = byteBuffer;
    }

    public ByteBuffer getByteBuffer() {
        return byteBuffer;
    }
}
