package nx.pingwheel.common.networking;

import java.util.UUID;

import org.jetbrains.annotations.Nullable;

import lombok.Getter;
import net.minecraft.util.math.Vec3d;

@Getter
public class PingLocationPacket {
    private String channel;
    private Vec3d pos;
    @Nullable
    private UUID entity;
	private int sequence;
	private int dimension;
    private UUID author;

    public PingLocationPacket(String channel, Vec3d pos, UUID entity, int sequence, int dimension, UUID author) {
        this.channel = channel;
        this.pos = pos;
        this.entity = entity;
        this.sequence = sequence;
        this.dimension = dimension;
        this.author = author;
    }
}
