package com.godlikehttp.Handlers;

import com.godlikehttp.GodlikeHttpPlugin;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Skill;

@Slf4j
public class SkillsHandler extends BaseHttpHandler
{
    @Override
    public String getName()
    {
        return "skills";
    }

    @Override
    public Object getData()
    {
        Client client = GodlikeHttpPlugin.getClient();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("attack_cur", client.getBoostedSkillLevel(Skill.ATTACK));
        jsonObject.addProperty("attack_max", client.getRealSkillLevel(Skill.ATTACK));
        jsonObject.addProperty("strength_cur", client.getBoostedSkillLevel(Skill.STRENGTH));
        jsonObject.addProperty("strength_max", client.getRealSkillLevel(Skill.STRENGTH));
        jsonObject.addProperty("defence_cur", client.getBoostedSkillLevel(Skill.DEFENCE));
        jsonObject.addProperty("defence_max", client.getRealSkillLevel(Skill.DEFENCE));
        jsonObject.addProperty("ranged_cur", client.getBoostedSkillLevel(Skill.RANGED));
        jsonObject.addProperty("ranged_max", client.getRealSkillLevel(Skill.RANGED));
        jsonObject.addProperty("prayer_cur", client.getBoostedSkillLevel(Skill.PRAYER));
        jsonObject.addProperty("prayer_max", client.getRealSkillLevel(Skill.PRAYER));
        jsonObject.addProperty("magic_cur", client.getBoostedSkillLevel(Skill.MAGIC));
        jsonObject.addProperty("magic_max", client.getRealSkillLevel(Skill.MAGIC));
        jsonObject.addProperty("runecraft_cur", client.getBoostedSkillLevel(Skill.RUNECRAFT));
        jsonObject.addProperty("runecraft_max", client.getRealSkillLevel(Skill.RUNECRAFT));
        jsonObject.addProperty("construction_cur", client.getBoostedSkillLevel(Skill.CONSTRUCTION));
        jsonObject.addProperty("construction_max", client.getRealSkillLevel(Skill.CONSTRUCTION));
        jsonObject.addProperty("hitpoints_cur", client.getBoostedSkillLevel(Skill.HITPOINTS));
        jsonObject.addProperty("hitpoints_max", client.getRealSkillLevel(Skill.HITPOINTS));
        jsonObject.addProperty("agility_cur", client.getBoostedSkillLevel(Skill.AGILITY));
        jsonObject.addProperty("agility_max", client.getRealSkillLevel(Skill.AGILITY));
        jsonObject.addProperty("herblore_cur", client.getBoostedSkillLevel(Skill.HERBLORE));
        jsonObject.addProperty("herblore_max", client.getRealSkillLevel(Skill.HERBLORE));
        jsonObject.addProperty("thieving_cur", client.getBoostedSkillLevel(Skill.THIEVING));
        jsonObject.addProperty("thieving_max", client.getRealSkillLevel(Skill.THIEVING));
        jsonObject.addProperty("crafting_cur", client.getBoostedSkillLevel(Skill.CRAFTING));
        jsonObject.addProperty("crafting_max", client.getRealSkillLevel(Skill.CRAFTING));
        jsonObject.addProperty("fletching_cur", client.getBoostedSkillLevel(Skill.FLETCHING));
        jsonObject.addProperty("fletching_max", client.getRealSkillLevel(Skill.FLETCHING));
        jsonObject.addProperty("slayer_cur", client.getBoostedSkillLevel(Skill.SLAYER));
        jsonObject.addProperty("slayer_max", client.getRealSkillLevel(Skill.SLAYER));
        jsonObject.addProperty("hunter_cur", client.getBoostedSkillLevel(Skill.HUNTER));
        jsonObject.addProperty("hunter_max", client.getRealSkillLevel(Skill.HUNTER));
        jsonObject.addProperty("mining_cur", client.getBoostedSkillLevel(Skill.MINING));
        jsonObject.addProperty("mining_max", client.getRealSkillLevel(Skill.MINING));
        jsonObject.addProperty("smithing_cur", client.getBoostedSkillLevel(Skill.SMITHING));
        jsonObject.addProperty("smithing_max", client.getRealSkillLevel(Skill.SMITHING));
        jsonObject.addProperty("fishing_cur", client.getBoostedSkillLevel(Skill.FISHING));
        jsonObject.addProperty("fishing_max", client.getRealSkillLevel(Skill.FISHING));
        jsonObject.addProperty("cooking_cur", client.getBoostedSkillLevel(Skill.COOKING));
        jsonObject.addProperty("cooking_max", client.getRealSkillLevel(Skill.COOKING));
        jsonObject.addProperty("firemaking_cur", client.getBoostedSkillLevel(Skill.FIREMAKING));
        jsonObject.addProperty("firemaking_max", client.getRealSkillLevel(Skill.FIREMAKING));
        jsonObject.addProperty("woodcutting_cur", client.getBoostedSkillLevel(Skill.WOODCUTTING));
        jsonObject.addProperty("woodcutting_max", client.getRealSkillLevel(Skill.WOODCUTTING));
        jsonObject.addProperty("farming_cur", client.getBoostedSkillLevel(Skill.FARMING));
        jsonObject.addProperty("farming_max", client.getRealSkillLevel(Skill.FARMING));
        
        return jsonObject;
    }
}
