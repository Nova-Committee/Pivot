package committee.nova.pivot.mixin;

import committee.nova.pivot.Constants;
import committee.nova.pivot.event.entity.PaintingPlacedEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Painting.class)
public abstract class PaintingMixin extends HangingEntity {
    @Shadow protected abstract void setVariant(Holder<PaintingVariant> holder);

    protected PaintingMixin(EntityType<? extends HangingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(
            method = "<init>(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;Lnet/minecraft/core/Holder;)V",
            at = @At("TAIL")
    )
    public void onInit(Level level, BlockPos blockPos, Direction direction, Holder<PaintingVariant> holder, CallbackInfo ci) {
        final Holder<PaintingVariant> motive = PaintingPlacedEvent.SET_VARIANT.invoker().set(level, holder, direction, level.isClientSide());
        final Direction dir = PaintingPlacedEvent.SET_DIRECTION.invoker().set(level, holder, direction, level.isClientSide());
        setVariant(motive);
        setDirection(dir);
        if (this.survives()) return;
        Constants.LOGGER.warn("Painting motive/direction replacement failed and skipped!");
        setVariant(holder);
        setDirection(direction);
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void dropItem(@Nullable Entity entity) {
    }

    @Override
    public void playPlacementSound() {
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return null;
    }
}
