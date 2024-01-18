package com.godlikehttp.Handlers;

import com.godlikehttp.GodlikeHttpPlugin;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Skill;

@Slf4j
public class PrayerHandler extends BaseHttpHandler
{
    @Override
    public String getName()
    {
        return "prayer";
    }

    @Override
    public Object getData()
    {
        Client client = GodlikeHttpPlugin.getClient();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("prayer_cur", client.getBoostedSkillLevel(Skill.PRAYER));
        jsonObject.addProperty("prayer_max", client.getRealSkillLevel(Skill.PRAYER));
        
        return jsonObject;
    }
}
