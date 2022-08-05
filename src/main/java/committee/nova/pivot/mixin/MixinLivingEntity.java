package committee.nova.pivot.mixin;

import committee.nova.pivot.event.entity.living.LivingChangeSprintingStatusEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {
    @Inject(method = "setSprinting", at = @At("HEAD"), cancellable = true)
    public void beforeSetSprinting(boolean b, CallbackInfo ci) {
        if (MinecraftForge.EVENT_BUS.post(new LivingChangeSprintingStatusEvent.Pre((LivingEntity) (Object) this, b)))
            ci.cancel();
    }

    @Inject(method = "setSprinting", at = @At("TAIL"))
    public void afterSetSprinting(boolean b, CallbackInfo ci) {
        MinecraftForge.EVENT_BUS.post(new LivingChangeSprintingStatusEvent.Post((LivingEntity) (Object) this, b));
    }
}
