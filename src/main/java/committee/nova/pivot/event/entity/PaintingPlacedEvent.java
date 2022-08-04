package committee.nova.pivot.event.entity;

import net.minecraft.core.Direction;
import net.minecraft.world.entity.decoration.Motive;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.Event;

public class PaintingPlacedEvent extends Event {
    private final Level level;
    private Motive motive;
    private Direction direction;
    private final boolean isClientSide;

    public PaintingPlacedEvent(Level level, Motive motive, Direction direction, boolean isClientSide) {
        this.level = level;
        this.motive = motive;
        this.direction = direction;
        this.isClientSide = isClientSide;
    }

    public Level getLevel() {
        return level;
    }

    public Motive getMotive() {
        return motive;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean isClientSide() {
        return isClientSide;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setMotive(Motive motive) {
        this.motive = motive;
    }
}
