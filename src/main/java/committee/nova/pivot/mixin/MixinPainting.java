package committee.nova.pivot.mixin;

import committee.nova.pivot.Pivot;
import committee.nova.pivot.event.entity.PaintingPlacedEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.Motive;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Painting.class)
public abstract class MixinPainting extends HangingEntity {
    @Shadow
    public Motive motive;

    protected MixinPainting(EntityType<? extends HangingEntity> e, Level l) {
        super(e, l);
    }

    @Inject(method = "<init>(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;)V",
            at = @At("TAIL"))
    public void onInit(Level level, BlockPos pos, Direction direction, CallbackInfo ci) {
        final Motive oldMotive = motive;
        final PaintingPlacedEvent placeEvent = new PaintingPlacedEvent(level, motive, direction, level.isClientSide);
        MinecraftForge.EVENT_BUS.post(placeEvent);
        motive = placeEvent.getMotive();
        final Direction newDirection = placeEvent.getDirection();
        setDirection(newDirection);
        if (this.survives()) return;
        Pivot.LOGGER.warn("Painting motive/direction replacement failed and skipped!");
        motive = oldMotive;
        setDirection(direction);
    }
}
