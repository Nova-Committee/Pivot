package committee.nova.pivot.event.entity.living;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

public class LivingChangeSprintingStatusEvent extends Event {
    private final LivingEntity entity;
    private final boolean isClientSide;

    private LivingChangeSprintingStatusEvent(LivingEntity entity) {
        this.entity = entity;
        this.isClientSide = entity.level.isClientSide;
    }

    public LivingEntity getEntity() {
        return entity;
    }

    public boolean isClientSide() {
        return isClientSide;
    }

    @Cancelable
    public static class Pre extends LivingChangeSprintingStatusEvent {
        private final boolean maySprint;

        public Pre(LivingEntity entity, boolean maySprint) {
            super(entity);
            this.maySprint = maySprint;
        }

        public boolean maySprint() {
            return maySprint;
        }
    }

    public static class Post extends LivingChangeSprintingStatusEvent {
        private final boolean willSprint;

        public Post(LivingEntity entity, boolean willSprint) {
            super(entity);
            this.willSprint = willSprint;
        }

        public boolean willSprint() {
            return willSprint;
        }
    }
}
