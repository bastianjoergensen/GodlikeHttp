package com.godlikehttp;

import com.godlikehttp.Handlers.*;
import com.google.inject.Provides;
import com.sun.net.httpserver.HttpServer;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.*;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.NpcLootReceived;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import javax.inject.Inject;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.concurrent.Executors;

@Slf4j
@PluginDescriptor(
        name = "GodlikeHttp"
)
public class GodlikeHttpPlugin extends Plugin
{
    public static GodlikeHttpPlugin instance;

    @Inject
    private Client client;

    @Inject
    private GodlikeHttpConfig config;

    @Inject
    private ItemManager itemManager;

    public HttpServer server;

    private EventBus eventBus;

    public HashSet<BaseHttpHandler> handlers;
    public HashSet<BaseHttpHandler> handlersQueued;


    @Provides
    GodlikeHttpConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(GodlikeHttpConfig.class);
    }

    @Override
    protected void startUp()
    {
        log.info("GodlikeHttpPlugin Started!");

        instance = this;

        eventBus = new EventBus();
        handlersQueued = new HashSet<>();

        this.startHttpServer();
    }

    @Override
    protected void shutDown()
    {
        log.info("GodlikeHttpPlugin stopped!");
        server.stop(1);
        
        for (BaseHttpHandler handler : handlers)
            eventBus.unregister(handler);
        
        handlers.clear();
        handlersQueued.clear();
    }

    @Subscribe
    public void onGameTick(final GameTick event)
    {
        eventBus.post(event);

        handlersQueued.clear();
        
        for (BaseHttpHandler handler : handlers)
        {
            handler.setCanTick(true);
        }
    }

    @Subscribe
    public void onGameStateChanged(GameStateChanged event)
    {
        eventBus.post(event);
    }

    @Subscribe
    public void onItemContainerChanged(final ItemContainerChanged event)
    {
        eventBus.post(event);
    }

    @Subscribe
    public void onInteractingChanged(InteractingChanged event)
    {
        eventBus.post(event);
    }

    @Subscribe
    public void onNpcChanged(NpcChanged event)
    {
        eventBus.post(event);
    }

    @Subscribe
    public void onNpcLootReceived(final NpcLootReceived event)
    {
        eventBus.post(event);
    }

    @Subscribe
    void onAnimationChanged(AnimationChanged event)
    {
        eventBus.post(event);
    }

    private void startHttpServer()
    {
        try
        {
            server = HttpServer.create(new InetSocketAddress(9420), 0);

            handlers = new HashSet<>();
            handlers.add(new AnimationHandler());
            handlers.add(new BankHandler());
            handlers.add(new EquipmentHandler());
            handlers.add(new GameHandler());
            handlers.add(new InventoryHandler());
            handlers.add(new LootHandler());
            handlers.add(new NpcHandler());
            handlers.add(new SkillsHandler());
            handlers.add(new StatusHandler());

            for (BaseHttpHandler handler : handlers)
            {
                log.info("Registered handler: api/" + handler.getName());

                eventBus.register(handler);
                server.createContext("/api/" + handler.getName(), handler);
            }

            server.setExecutor(Executors.newSingleThreadExecutor());
//            server.setExecutor(Executors.newFixedThreadPool(10));
            server.start();

            log.info("GodlikeHttpPlugin HTTP server started!");
        }
        catch (IOException e)
        {
            log.error("Error starting HTTP server", e);
        }
    }

    public static Client getClient()
    {
        return instance.client;
    }

    public static GodlikeHttpConfig getConfig()
    {
        return instance.config;
    }

    public static ItemManager getItemManager()
    {
        return instance.itemManager;
    }
}
