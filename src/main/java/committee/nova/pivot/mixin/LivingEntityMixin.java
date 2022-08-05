package committee.nova.pivot.mixin;

import committee.nova.pivot.event.entity.living.LivingChangeSprintingStatusEvent;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(
            method = "setSprinting",
            at = @At("HEAD"),
            cancellable = true
    )
    public void preSetSprinting(boolean bl, CallbackInfo ci) {
        if (!LivingChangeSprintingStatusEvent.PRE_SPRINT.invoker().preSprint((LivingEntity)((Object)this), bl))
            ci.cancel();
    }

    @Inject(
            method = "setSprinting",
            at = @At("TAIL")
    )
    public void postSetSprinting(boolean bl, CallbackInfo ci) {
        LivingChangeSprintingStatusEvent.POST_SPRINT.invoker().postSprint((LivingEntity)((Object)this), bl);
    }
}
