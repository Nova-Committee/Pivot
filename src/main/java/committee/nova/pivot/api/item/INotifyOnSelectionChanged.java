package committee.nova.pivot.api.item;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface INotifyOnSelectionChanged {
    default void onSelected(Player player, ItemStack stack, int slot) {

    }

    default void onUnselected(Player player, ItemStack stack, int slot) {

    }
}
