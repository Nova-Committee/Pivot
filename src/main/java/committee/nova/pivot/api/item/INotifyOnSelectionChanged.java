package committee.nova.pivot.api.item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;

public interface INotifyOnSelectionChanged {
    default void onSelected(Entity entity, ItemStack stack, int slot) {

    }

    default void onUnselected(Entity entity, ItemStack stack, int slot) {

    }
}
