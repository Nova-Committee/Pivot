package committee.nova.pivot.mixin;

import committee.nova.pivot.event.item.ItemStackSelectionChangedEvent;
import committee.nova.pivot.reference.TagStringReference;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStack.class)
public abstract class MixinItemStack {
    @Shadow
    public abstract CompoundTag getOrCreateTag();

    @Inject(method = "inventoryTick", at = @At("TAIL"))
    public void onInventoryTick(Level level, Entity entity, int slot, boolean isSelected, CallbackInfo ci) {
        if (level.isClientSide) return;
        final var tag = getOrCreateTag();
        final var wasSelected = tag.getBoolean(TagStringReference.WAS_SELECTED.name);
        if (wasSelected == isSelected) return;
        MinecraftForge.EVENT_BUS.post(new ItemStackSelectionChangedEvent(entity, (ItemStack) (Object) this, wasSelected, slot));
        tag.putBoolean(TagStringReference.WAS_SELECTED.name, isSelected);
    }
}
