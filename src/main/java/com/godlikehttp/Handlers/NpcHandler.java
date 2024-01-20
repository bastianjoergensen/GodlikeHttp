package com.godlikehttp.Handlers;

import com.godlikehttp.GodlikeHttpPlugin;
import com.godlikehttp.Helpers;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Actor;
import net.runelite.api.NPC;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.InteractingChanged;
import net.runelite.api.events.NpcChanged;
import net.runelite.client.eventbus.Subscribe;

@Slf4j
public class NpcHandler extends BaseHttpHandler
{
    private int id;
    private int healthCur;
    private int healthMax;
    private int combatLevel;
    private boolean isDead;


    @Override
    public String getName()
    {
        return "npc";
    }

    @Override
    public Object getData()
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("health_cur", healthCur);
        jsonObject.addProperty("health_max", healthMax);
        jsonObject.addProperty("combat_level", combatLevel);
        jsonObject.addProperty("is_dead", isDead);

        id = 0;
        healthCur = 0;
        healthMax = 0;
        combatLevel = 0;
        isDead = false;

        return jsonObject;
    }

    @Subscribe
    public void onGameTick(GameTick event)
    {
        Actor actor = GodlikeHttpPlugin.getClient().getLocalPlayer().getInteracting();
        if (!(actor instanceof NPC))
            return;

        NPC npc = (NPC) actor;
        updateNpc(npc);
    }

    @Subscribe
    public void onInteractingChanged(InteractingChanged event)
    {
        final Actor source = event.getSource();
        if (source != GodlikeHttpPlugin.getClient().getLocalPlayer())
            return;

        final Actor target = event.getTarget();
        if (!(target instanceof NPC))
            return;

        NPC npc = (NPC) event.getTarget();
        updateNpc(npc);
    }

    @Subscribe
    public void onNpcChanged(NpcChanged event)
    {
        NPC npc = event.getNpc();
        if (GodlikeHttpPlugin.getClient().getLocalPlayer().getInteracting() != npc)
            return;

        updateNpc(npc);
    }

    private void updateNpc(NPC npc)
    {
        id = npc.getId();
        healthCur = Helpers.getPercent(npc.getHealthRatio(), npc.getHealthScale());
        healthMax = 100;
        combatLevel = npc.getCombatLevel();
        isDead = npc.isDead();
    }
}
