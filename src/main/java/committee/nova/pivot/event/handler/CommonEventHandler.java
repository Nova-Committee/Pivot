package committee.nova.pivot.event.handler;

import committee.nova.pivot.api.item.INotifyOnSelectionChanged;
import committee.nova.pivot.event.item.ItemStackSelectionChangedEvent;
import committee.nova.pivot.reference.TagStringReference;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CommonEventHandler {
    @SubscribeEvent
    public static void onSelectionChanged(final ItemStackSelectionChangedEvent event) {
        final Player player = event.getOwner();
        final ItemStack currentStack = event.getCurrentStack();
        final Item current = currentStack.getItem();
        if (current instanceof INotifyOnSelectionChanged)
            ((INotifyOnSelectionChanged) current).onSelected(player, currentStack, event.getCurrentSlot());
        final ItemStack pastStack = event.getPastStack();
        final Item past = pastStack.getItem();
        if (past instanceof INotifyOnSelectionChanged)
            ((INotifyOnSelectionChanged) past).onUnselected(player, pastStack, event.getPastSlot());
    }

    @SubscribeEvent
    public static void onTick(final TickEvent.PlayerTickEvent event) {
        if (event.side.isClient() || event.phase == TickEvent.Phase.START) return;
        final Player player = event.player;
        final CompoundTag tag = player.getPersistentData();
        final Inventory inv = player.getInventory();
        final int pastSelected = tag.getInt(TagStringReference.SELECTED_SLOT.name);
        final int currentSelected = inv.selected;
        if (pastSelected == currentSelected) return;
        MinecraftForge.EVENT_BUS.post(new ItemStackSelectionChangedEvent(player, inv.getItem(currentSelected), inv.getItem(pastSelected), currentSelected, pastSelected));
        tag.putInt(TagStringReference.SELECTED_SLOT.name, currentSelected);
    }
}
