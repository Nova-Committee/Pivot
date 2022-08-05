package committee.nova.pivot.event.entity.living;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Cancelable;

public class LivingChangeSprintingStatusEvent extends LivingEvent {
    private final boolean isClientSide;

    private LivingChangeSprintingStatusEvent(LivingEntity entity) {
        super(entity);
        this.isClientSide = entity.level.isClientSide;
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
