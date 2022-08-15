package committee.nova.pivot.event.item;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.Event;

public class ItemStackSelectionChangedEvent extends Event {
    private final Player owner;
    private final int currentSlot;
    private final int pastSlot;
    private final ItemStack currentSelected;
    private final ItemStack pastSelected;

    public ItemStackSelectionChangedEvent(Player owner, ItemStack currentSelected, ItemStack pastSelected, int currentSlot, int pastSlot) {
        this.owner = owner;
        this.currentSelected = currentSelected;
        this.pastSelected = pastSelected;
        this.currentSlot = currentSlot;
        this.pastSlot = pastSlot;
    }

    public Player getOwner() {
        return owner;
    }

    public ItemStack getCurrentStack() {
        return currentSelected;
    }

    public ItemStack getPastStack() {
        return pastSelected;
    }

    public int getCurrentSlot() {
        return currentSlot;
    }

    public int getPastSlot() {
        return pastSlot;
    }
}
