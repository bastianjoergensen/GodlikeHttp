package com.godlikehttp.Handlers;

import com.godlikehttp.GodlikeHttpPlugin;
import com.godlikehttp.Helpers;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Player;
import net.runelite.api.Skill;
import net.runelite.api.events.GameTick;
import net.runelite.client.eventbus.Subscribe;

@Slf4j
public class StatusHandler extends BaseHttpHandler
{
    private int healthCur;
    private int healthMax;
    private int prayerCur;
    private int prayerMax;
    private int runEnergy;
    private int weight;
    private int combatLevel;
    private boolean isDead;
    private boolean isInteracting;
    private boolean isIdle;
    private boolean isWalking;
    private boolean isRunning;


    @Override
    public String getName()
    {
        return "status";
    }

    @Override
    public Object getData()
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("health_cur", healthCur);
        jsonObject.addProperty("health_max", healthMax);
        jsonObject.addProperty("prayer_cur", prayerCur);
        jsonObject.addProperty("prayer_max", prayerMax);
        jsonObject.addProperty("run_energy", runEnergy);
        jsonObject.addProperty("weight", weight);
        jsonObject.addProperty("combat_level", combatLevel);
        jsonObject.addProperty("is_dead", isDead);
        jsonObject.addProperty("is_interacting", isInteracting);
        jsonObject.addProperty("is_idle", isIdle);
        jsonObject.addProperty("is_moving", isWalking || isRunning);
        jsonObject.addProperty("is_walking", isWalking);
        jsonObject.addProperty("is_running", isRunning);
        return jsonObject;
    }

    @Subscribe
    void onGameTick(final GameTick event)
    {
        Client client = GodlikeHttpPlugin.getClient();
        if (client == null)
            return;

        Player localPlayer = client.getLocalPlayer();
        if (localPlayer == null)
            return;

        healthCur = Helpers.getPercent(localPlayer.getHealthRatio(), localPlayer.getHealthScale());
        healthMax = 100;

        prayerCur = Helpers.getPercent(client.getBoostedSkillLevel(Skill.PRAYER), client.getRealSkillLevel(Skill.PRAYER));
        prayerMax = 100;

        runEnergy = client.getEnergy();
        weight = client.getWeight();

        combatLevel = localPlayer.getCombatLevel();

        isDead = localPlayer.isDead();
        isInteracting = localPlayer.isInteracting();
        
        isIdle = localPlayer.getAnimation() == -1 && localPlayer.getPoseAnimation() == localPlayer.getIdlePoseAnimation();
        isWalking = localPlayer.getPoseAnimation() == localPlayer.getWalkAnimation() || localPlayer.getPoseAnimation() == 820 || localPlayer.getPoseAnimation() == 821 || localPlayer.getPoseAnimation() == 822;
        isRunning = localPlayer.getPoseAnimation() == localPlayer.getRunAnimation();
    }
}
