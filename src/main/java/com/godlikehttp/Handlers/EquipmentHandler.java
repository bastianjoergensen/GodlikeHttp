package com.godlikehttp.Handlers;

import com.godlikehttp.GodlikeHttpPlugin;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.InventoryID;
import net.runelite.api.Item;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemManager;
import net.runelite.http.api.RuneLiteAPI;

@Slf4j
public class EquipmentHandler extends BaseHttpHandler
{
    private Item[] equipmentState;
    private int itemCount;
    private int gePrice;


    @Override
    public String getName()
    {
        return "equipment";
    }

    @Override
    public Object getData()
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("item_count", itemCount);
        jsonObject.addProperty("ge_price", gePrice);
        jsonObject.add("items", RuneLiteAPI.GSON.toJsonTree(equipmentState));
        return jsonObject;
    }

    @Subscribe
    public void onItemContainerChanged(final ItemContainerChanged event)
    {
        if (event.getItemContainer() == GodlikeHttpPlugin.getClient().getItemContainer(InventoryID.EQUIPMENT))
        {
            ItemManager itemManager = GodlikeHttpPlugin.getItemManager();

            equipmentState = event.getItemContainer().getItems();
            itemCount = 0;
            gePrice = 0;
            
            for (Item i : equipmentState)
            {
                if (i.getId() != -1 && i.getQuantity() >= 0)
                {
                    itemCount++;
                    gePrice += i.getQuantity() * itemManager.getItemComposition(i.getId()).getPrice();
                }
            }
        }
    }
}
