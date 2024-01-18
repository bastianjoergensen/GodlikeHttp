package com.godlikehttp.Handlers;

import com.godlikehttp.GodlikeHttpPlugin;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Skill;

@Slf4j
public class HealthHandler extends BaseHttpHandler
{
    @Override
    public String getName()
    {
        return "health";
    }

    @Override
    public Object getData()
    {
        Client client = GodlikeHttpPlugin.getClient();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("health_cur", client.getBoostedSkillLevel(Skill.HITPOINTS));
        jsonObject.addProperty("health_max", client.getRealSkillLevel(Skill.HITPOINTS));
        
        return jsonObject;
    }
}
