package com.godlikehttp.Handlers;

import com.godlikehttp.GodlikeHttpPlugin;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Item;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.NpcLootReceived;
import net.runelite.client.game.ItemManager;
import net.runelite.client.game.ItemStack;
import net.runelite.http.api.RuneLiteAPI;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class LootHandler extends BaseHttpHandler
{
    private int npcId;
    private int gePrice;
    private List<Item> items;


    @Override
    public String getName()
    {
        return "loot";
    }

    @Override
    public Object getData()
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("npc_id", npcId);
        jsonObject.addProperty("ge_price", gePrice);
        jsonObject.add("items", RuneLiteAPI.GSON.toJsonTree(items));
        
        npcId = 0;
        gePrice = 0;
        items = null;
        
        return jsonObject;
    }

    @Subscribe
    public void onNpcLootReceived(final NpcLootReceived event)
    {
        ItemManager itemManager = GodlikeHttpPlugin.getItemManager();

        npcId = event.getNpc().getId();
        gePrice = 0;
        items = new ArrayList<>();
        
        for (ItemStack i : event.getItems())
        {
            items.add(new Item(i.getId(), i.getQuantity()));
            gePrice += i.getQuantity() * itemManager.getItemComposition(i.getId()).getPrice();
        }
    }
}
