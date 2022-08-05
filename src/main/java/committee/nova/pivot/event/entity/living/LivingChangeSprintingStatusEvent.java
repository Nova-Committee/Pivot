package committee.nova.pivot.event.entity.living;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.entity.LivingEntity;

/**
 * Callback for listening to entity sprinting.<p>
 * Called before and after an entity tries to sprint.
 */
public class LivingChangeSprintingStatusEvent {
    /**
     * Called before an entity tries to sprint.<p>
     * It doesn't do side check for you, so you'll have to do this yourself!<p>
     * Upon return:<p>
     * - true continues with normal sprinting behavior.<p>
     * - false cancels further processing and does not allow sprinting.
     */
    public static Event<Pre> PRE_SPRINT = EventFactory.createArrayBacked(Pre.class,
            (listeners) -> (entity, maySprint) -> {
                for (Pre listener : listeners) {
                    if (listener.preSprint(entity, maySprint)) return true;
                }
                return false;
            });

    /**
     * Called after an entity tries to sprint.<p>
     * It doesn't do side check for you, so you'll have to do this yourself!
     */
    public static Event<Post> POST_SPRINT = EventFactory.createArrayBacked(Post.class,
            (listeners) -> (entity, maySprint) -> {
                for (Post listener : listeners) {
                    listener.postSprint(entity, maySprint);
                }
            });

    public interface Pre {
        boolean preSprint(LivingEntity entity, boolean maySprint);
    }

    public interface Post {
        void postSprint(LivingEntity entity, boolean willSprint);
    }
}
