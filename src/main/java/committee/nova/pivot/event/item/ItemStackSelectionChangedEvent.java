package committee.nova.pivot.event.item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.Event;

public class ItemStackSelectionChangedEvent extends Event {
    private final Entity owner;
    private final ItemStack stack;
    private final boolean wasSelected;
    private final int slot;

    public ItemStackSelectionChangedEvent(Entity owner, ItemStack stack, boolean wasSelected, int slot) {
        this.owner = owner;
        this.stack = stack;
        this.wasSelected = wasSelected;
        this.slot = slot;
    }

    public Entity getOwner() {
        return owner;
    }

    public ItemStack getStack() {
        return stack;
    }

    public boolean wasSelected() {
        return wasSelected;
    }

    public boolean isSelected() {
        return !wasSelected;
    }

    public int getSlot() {
        return slot;
    }
}
