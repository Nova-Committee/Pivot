package committee.nova.pivot.event.handler;

import committee.nova.pivot.api.item.INotifyOnSelectionChanged;
import committee.nova.pivot.event.item.ItemStackSelectionChangedEvent;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CommonEventHandler {
    @SubscribeEvent
    public static void onSelectionChanged(final ItemStackSelectionChangedEvent event) {
        final ItemStack stack = event.getStack();
        if (!(stack.getItem() instanceof INotifyOnSelectionChanged)) return;
        final INotifyOnSelectionChanged n = (INotifyOnSelectionChanged) stack.getItem();
        if (event.wasSelected()) {
            n.onUnselected(event.getOwner(), stack, event.getSlot());
        } else n.onSelected(event.getOwner(), stack, event.getSlot());
    }
}
