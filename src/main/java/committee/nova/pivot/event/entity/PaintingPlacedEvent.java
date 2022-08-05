package committee.nova.pivot.event.entity;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.level.Level;

/**
 * Callback for placing a painting.<p>
 * Called before a painting is placed.
 */
public class PaintingPlacedEvent {

    /**
     * Setting the variant of this painting.
     */
    public static Event<SetPaintingVariant> SET_VARIANT = EventFactory.createArrayBacked(SetPaintingVariant.class,
            (listeners) -> (level, motive, direction, isClientSide) -> {
                for (SetPaintingVariant listener : listeners) {
                    Holder<PaintingVariant> variant = listener.set(level, motive, direction, isClientSide);
                    if (variant != null) return variant;
                }
                return motive;
            });

    /**
     * Setting the direction of this painting.
     */
    public static Event<SetPaintingDirection> SET_DIRECTION = EventFactory.createArrayBacked(SetPaintingDirection.class,
            (listeners) -> (level, motive, direction, isClientSide) -> {
                for (SetPaintingDirection listener : listeners) {
                    Direction dir = listener.set(level, motive, direction, isClientSide);
                    if (dir != null) return dir;
                }
                return direction;
            });

    public interface SetPaintingVariant {
        Holder<PaintingVariant> set(Level level, Holder<PaintingVariant> motive, Direction direction, boolean isClientSide);
    }

    public interface SetPaintingDirection {
        Direction set(Level level, Holder<PaintingVariant> motive, Direction direction, boolean isClientSide);
    }
}
