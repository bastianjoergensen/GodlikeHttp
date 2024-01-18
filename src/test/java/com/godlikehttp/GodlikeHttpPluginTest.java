package com.godlikehttp;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class GodlikeHttpPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(GodlikeHttpPlugin.class);
		RuneLite.main(args);
	}
}
