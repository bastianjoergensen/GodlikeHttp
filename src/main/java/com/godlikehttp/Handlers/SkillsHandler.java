package com.godlikehttp.Handlers;

import com.godlikehttp.GodlikeHttpPlugin;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Player;
import net.runelite.api.Skill;
import net.runelite.api.events.GameTick;
import net.runelite.client.eventbus.Subscribe;

@Slf4j
public class SkillsHandler extends BaseHttpHandler
{
    private int attackCur;
    private int attackMax;
    private int attackXp;

    private int strengthCur;
    private int strengthMax;
    private int strengthXp;

    private int defenceCur;
    private int defenceMax;
    private int defenceXp;

    private int rangedCur;
    private int rangedMax;
    private int rangedXp;

    private int prayerCur;
    private int prayerMax;
    private int prayerXp;

    private int magicCur;
    private int magicMax;
    private int magicXp;

    private int runecraftCur;
    private int runecraftMax;
    private int runecraftXp;

    private int constructionCur;
    private int constructionMax;
    private int constructionXp;

    private int hitpointsCur;
    private int hitpointsMax;
    private int hitpointsXp;

    private int agilityCur;
    private int agilityMax;
    private int agilityXp;

    private int herbloreCur;
    private int herbloreMax;
    private int herbloreXp;

    private int thievingCur;
    private int thievingMax;
    private int thievingXp;

    private int craftingCur;
    private int craftingMax;
    private int craftingXp;

    private int fletchingCur;
    private int fletchingMax;
    private int fletchingXp;

    private int slayerCur;
    private int slayerMax;
    private int slayerXp;

    private int hunterCur;
    private int hunterMax;
    private int hunterXp;

    private int miningCur;
    private int miningMax;
    private int miningXp;

    private int smithingCur;
    private int smithingMax;
    private int smithingXp;

    private int fishingCur;
    private int fishingMax;
    private int fishingXp;

    private int cookingCur;
    private int cookingMax;
    private int cookingXp;

    private int firemakingCur;
    private int firemakingMax;
    private int firemakingXp;

    private int woodcuttingCur;
    private int woodcuttingMax;
    private int woodcuttingXp;

    private int farmingCur;
    private int farmingMax;
    private int farmingXp;

    private int total;

    @Override
    public String getName()
    {
        return "skills";
    }

    @Override
    public Object getData()
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("attack_cur", attackCur);
        jsonObject.addProperty("attack_max", attackMax);
        jsonObject.addProperty("attack_xp", attackXp);

        jsonObject.addProperty("strength_cur", strengthCur);
        jsonObject.addProperty("strength_max", strengthMax);
        jsonObject.addProperty("strength_xp", strengthXp);

        jsonObject.addProperty("defence_cur", defenceCur);
        jsonObject.addProperty("defence_max", defenceMax);
        jsonObject.addProperty("defence_xp", defenceXp);

        jsonObject.addProperty("ranged_cur", rangedCur);
        jsonObject.addProperty("ranged_max", rangedMax);
        jsonObject.addProperty("ranged_xp", rangedXp);

        jsonObject.addProperty("prayer_cur", prayerCur);
        jsonObject.addProperty("prayer_max", prayerMax);
        jsonObject.addProperty("prayer_xp", prayerXp);

        jsonObject.addProperty("magic_cur", magicCur);
        jsonObject.addProperty("magic_max", magicMax);
        jsonObject.addProperty("magic_xp", magicXp);

        jsonObject.addProperty("runecraft_cur", runecraftCur);
        jsonObject.addProperty("runecraft_max", runecraftMax);
        jsonObject.addProperty("runecraft_xp", runecraftXp);

        jsonObject.addProperty("construction_cur", constructionCur);
        jsonObject.addProperty("construction_max", constructionMax);
        jsonObject.addProperty("construction_xp", constructionXp);

        jsonObject.addProperty("hitpoints_cur", hitpointsCur);
        jsonObject.addProperty("hitpoints_max", hitpointsMax);
        jsonObject.addProperty("hitpoints_xp", hitpointsXp);

        jsonObject.addProperty("agility_cur", agilityCur);
        jsonObject.addProperty("agility_max", agilityMax);
        jsonObject.addProperty("agility_xp", agilityXp);

        jsonObject.addProperty("herblore_cur", herbloreCur);
        jsonObject.addProperty("herblore_max", herbloreMax);
        jsonObject.addProperty("herblore_xp", herbloreXp);

        jsonObject.addProperty("thieving_cur", thievingCur);
        jsonObject.addProperty("thieving_max", thievingMax);
        jsonObject.addProperty("thieving_xp", thievingXp);

        jsonObject.addProperty("crafting_cur", craftingCur);
        jsonObject.addProperty("crafting_max", craftingMax);
        jsonObject.addProperty("crafting_xp", craftingXp);

        jsonObject.addProperty("fletching_cur", fletchingCur);
        jsonObject.addProperty("fletching_max", fletchingMax);
        jsonObject.addProperty("fletching_xp", fletchingXp);

        jsonObject.addProperty("slayer_cur", slayerCur);
        jsonObject.addProperty("slayer_max", slayerMax);
        jsonObject.addProperty("slayer_xp", slayerXp);

        jsonObject.addProperty("hunter_cur", hunterCur);
        jsonObject.addProperty("hunter_max", hunterMax);
        jsonObject.addProperty("hunter_xp", hunterXp);

        jsonObject.addProperty("mining_cur", miningCur);
        jsonObject.addProperty("mining_max", miningMax);
        jsonObject.addProperty("mining_xp", miningXp);

        jsonObject.addProperty("smithing_cur", smithingCur);
        jsonObject.addProperty("smithing_max", smithingMax);
        jsonObject.addProperty("smithing_xp", smithingXp);

        jsonObject.addProperty("fishing_cur", fishingCur);
        jsonObject.addProperty("fishing_max", fishingMax);
        jsonObject.addProperty("fishing_xp", fishingXp);

        jsonObject.addProperty("cooking_cur", cookingCur);
        jsonObject.addProperty("cooking_max", cookingMax);
        jsonObject.addProperty("cooking_xp", cookingXp);

        jsonObject.addProperty("firemaking_cur", firemakingCur);
        jsonObject.addProperty("firemaking_max", firemakingMax);
        jsonObject.addProperty("firemaking_xp", firemakingXp);

        jsonObject.addProperty("woodcutting_cur", woodcuttingCur);
        jsonObject.addProperty("woodcutting_max", woodcuttingMax);
        jsonObject.addProperty("woodcutting_xp", woodcuttingXp);

        jsonObject.addProperty("farming_cur", farmingCur);
        jsonObject.addProperty("farming_max", farmingMax);
        jsonObject.addProperty("farming_xp", farmingXp);

        jsonObject.addProperty("total", total);
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

        attackCur = client.getBoostedSkillLevel(Skill.ATTACK);
        attackMax = client.getRealSkillLevel(Skill.ATTACK);
        attackXp = client.getSkillExperience(Skill.ATTACK);

        strengthCur = client.getBoostedSkillLevel(Skill.STRENGTH);
        strengthMax = client.getRealSkillLevel(Skill.STRENGTH);
        strengthXp = client.getSkillExperience(Skill.STRENGTH);

        defenceCur = client.getBoostedSkillLevel(Skill.DEFENCE);
        defenceMax = client.getRealSkillLevel(Skill.DEFENCE);
        defenceXp = client.getSkillExperience(Skill.DEFENCE);

        rangedCur = client.getBoostedSkillLevel(Skill.RANGED);
        rangedMax = client.getRealSkillLevel(Skill.RANGED);
        rangedXp = client.getSkillExperience(Skill.RANGED);

        prayerCur = client.getBoostedSkillLevel(Skill.PRAYER);
        prayerMax = client.getRealSkillLevel(Skill.PRAYER);
        prayerXp = client.getSkillExperience(Skill.PRAYER);

        magicCur = client.getBoostedSkillLevel(Skill.MAGIC);
        magicMax = client.getRealSkillLevel(Skill.MAGIC);
        magicXp = client.getSkillExperience(Skill.MAGIC);

        runecraftCur = client.getBoostedSkillLevel(Skill.RUNECRAFT);
        runecraftMax = client.getRealSkillLevel(Skill.RUNECRAFT);
        runecraftXp = client.getSkillExperience(Skill.RUNECRAFT);

        constructionCur = client.getBoostedSkillLevel(Skill.CONSTRUCTION);
        constructionMax = client.getRealSkillLevel(Skill.CONSTRUCTION);
        constructionXp = client.getSkillExperience(Skill.CONSTRUCTION);

        hitpointsCur = client.getBoostedSkillLevel(Skill.HITPOINTS);
        hitpointsMax = client.getRealSkillLevel(Skill.HITPOINTS);
        hitpointsXp = client.getSkillExperience(Skill.HITPOINTS);

        agilityCur = client.getBoostedSkillLevel(Skill.AGILITY);
        agilityMax = client.getRealSkillLevel(Skill.AGILITY);
        agilityXp = client.getSkillExperience(Skill.AGILITY);

        herbloreCur = client.getBoostedSkillLevel(Skill.HERBLORE);
        herbloreMax = client.getRealSkillLevel(Skill.HERBLORE);
        herbloreXp = client.getSkillExperience(Skill.HERBLORE);

        thievingCur = client.getBoostedSkillLevel(Skill.THIEVING);
        thievingMax = client.getRealSkillLevel(Skill.THIEVING);
        thievingXp = client.getSkillExperience(Skill.THIEVING);

        craftingCur = client.getBoostedSkillLevel(Skill.CRAFTING);
        craftingMax = client.getRealSkillLevel(Skill.CRAFTING);
        craftingXp = client.getSkillExperience(Skill.CRAFTING);

        fletchingCur = client.getBoostedSkillLevel(Skill.FLETCHING);
        fletchingMax = client.getRealSkillLevel(Skill.FLETCHING);
        fletchingXp = client.getSkillExperience(Skill.FLETCHING);

        slayerCur = client.getBoostedSkillLevel(Skill.SLAYER);
        slayerMax = client.getRealSkillLevel(Skill.SLAYER);
        slayerXp = client.getSkillExperience(Skill.SLAYER);

        hunterCur = client.getBoostedSkillLevel(Skill.HUNTER);
        hunterMax = client.getRealSkillLevel(Skill.HUNTER);
        hunterXp = client.getSkillExperience(Skill.HUNTER);

        miningCur = client.getBoostedSkillLevel(Skill.MINING);
        miningMax = client.getRealSkillLevel(Skill.MINING);
        miningXp = client.getSkillExperience(Skill.MINING);

        smithingCur = client.getBoostedSkillLevel(Skill.SMITHING);
        smithingMax = client.getRealSkillLevel(Skill.SMITHING);
        smithingXp = client.getSkillExperience(Skill.SMITHING);

        fishingCur = client.getBoostedSkillLevel(Skill.FISHING);
        fishingMax = client.getRealSkillLevel(Skill.FISHING);
        fishingXp = client.getSkillExperience(Skill.FISHING);

        cookingCur = client.getBoostedSkillLevel(Skill.COOKING);
        cookingMax = client.getRealSkillLevel(Skill.COOKING);
        cookingXp = client.getSkillExperience(Skill.COOKING);

        firemakingCur = client.getBoostedSkillLevel(Skill.FIREMAKING);
        firemakingMax = client.getRealSkillLevel(Skill.FIREMAKING);
        firemakingXp = client.getSkillExperience(Skill.FIREMAKING);

        woodcuttingCur = client.getBoostedSkillLevel(Skill.WOODCUTTING);
        woodcuttingMax = client.getRealSkillLevel(Skill.WOODCUTTING);
        woodcuttingXp = client.getSkillExperience(Skill.WOODCUTTING);

        farmingCur = client.getBoostedSkillLevel(Skill.FARMING);
        farmingMax = client.getRealSkillLevel(Skill.FARMING);
        farmingXp = client.getSkillExperience(Skill.FARMING);

        total = client.getTotalLevel();
    }
}
