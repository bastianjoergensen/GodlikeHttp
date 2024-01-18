package com.godlikehttp.Handlers;

import com.godlikehttp.GodlikeHttpPlugin;
import com.godlikehttp.Helpers;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.InventoryID;
import net.runelite.api.Item;
import net.runelite.api.ItemContainer;
import net.runelite.api.events.ItemContainerChanged;

@Slf4j
public class InventoryHandler extends BaseHttpHandler
{
    private Item[] inventoryState;


    @Override
    public String getName()
    {
        return "inventory";
    }

    @Override
    public Object getData()
    {
        return inventoryState;
    }

    public void onInventoryChanged(final ItemContainerChanged event)
    {
        if (event.getItemContainer() != GodlikeHttpPlugin.getClient().getItemContainer(InventoryID.INVENTORY))
            return;

        if (inventoryState == null)
        {
            log.info("Inventory state initialized, serializing...");
            inventoryState = event.getItemContainer().getItems();
            return;
        }

        ItemContainer currentContainer = event.getItemContainer();
        if (!Helpers.checkItemContainerEqual(inventoryState, currentContainer.getItems()))
        {
            log.info("Inventory state changed, serializing...");
            inventoryState = currentContainer.getItems();
        }
    }
}
