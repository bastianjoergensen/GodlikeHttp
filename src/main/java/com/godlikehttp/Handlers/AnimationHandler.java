package com.godlikehttp.Handlers;

import com.godlikehttp.GodlikeHttpPlugin;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Player;
import net.runelite.api.events.GameTick;
import net.runelite.client.eventbus.Subscribe;

@Slf4j
public class AnimationHandler extends BaseHttpHandler
{
    private int id;
    private int pose;


    @Override
    public String getName()
    {
        return "animation";
    }

    @Override
    public Object getData()
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("pose", pose);
        return jsonObject;
    }

    @Subscribe
    void onGameTick(final GameTick event)
    {
        Player localPlayer = GodlikeHttpPlugin.getClient().getLocalPlayer();
        if (localPlayer == null)
            return;

        id = localPlayer.getAnimation();
        pose = localPlayer.getPoseAnimation();
    }
}
