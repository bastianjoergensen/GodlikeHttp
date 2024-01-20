package com.godlikehttp.Handlers;

import com.godlikehttp.GodlikeHttpPlugin;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.eventbus.Subscribe;

@Slf4j
public class GameHandler extends BaseHttpHandler
{
    private String state;


    @Override
    public String getName()
    {
        return "game";
    }

    @Override
    public Object getData()
    {
        Client client = GodlikeHttpPlugin.getClient();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("state", state);
        jsonObject.addProperty("cycle", client != null ? client.getGameCycle() : -1);
        jsonObject.addProperty("tick", client != null ? client.getTickCount() : -1);
        return jsonObject;
    }

    @Subscribe
    public void onGameStateChanged(GameStateChanged event)
    {
        this.state = event.getGameState().name();
    }
}
