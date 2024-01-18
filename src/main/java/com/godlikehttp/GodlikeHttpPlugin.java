package com.godlikehttp;

import com.godlikehttp.Handlers.*;
import com.google.gson.JsonObject;
import com.google.inject.Provides;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.InventoryID;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.http.api.RuneLiteAPI;

import javax.inject.Inject;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.util.*;
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

    public HashSet<BaseHttpHandler> handlersProcessed;
    public Queue<BaseHttpHandler> handlersQueued;

    private InventoryHandler inventoryHandler;
    private HealthHandler healthHandler;
    private PrayerHandler prayerHandler;
    private SkillsHandler skillsHandler;


    @Provides
    GodlikeHttpConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(GodlikeHttpConfig.class);
    }

    @Override
    protected void startUp() throws Exception
    {
        log.info("GodlikeHttpPlugin Started!");

        instance = this;

        handlersProcessed = new HashSet<>();
        handlersQueued = new LinkedList<>();

        // Start the HTTP server
        this.startHttpServer();
    }

    @Override
    protected void shutDown()
    {
        log.info("GodlikeHttpPlugin stopped!");
        server.stop(1);
    }

    @Subscribe
    public void onGameTick(final GameTick event)
    {
        // Call your method here
        handleHttpExchange();
        handlersProcessed.clear();
    }

    @Subscribe
    public void onGameStateChanged(GameStateChanged state)
    {

    }

    @Subscribe
    public void onItemContainerChanged(final ItemContainerChanged event)
    {
        if (event.getItemContainer() == client.getItemContainer(InventoryID.INVENTORY))
            inventoryHandler.onInventoryChanged(event);
    }

    public void handleHttpExchange()
    {
        try
        {
            while (!handlersQueued.isEmpty())
            {
                BaseHttpHandler handler = handlersQueued.poll();
                HttpExchange exchange = handler.getCurrentExchange();
                exchange.sendResponseHeaders(200, 0);

                try (OutputStreamWriter out = new OutputStreamWriter(exchange.getResponseBody()))
                {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.add(handler.getName(), RuneLiteAPI.GSON.toJsonTree(handler.getData()));

                    log.info("Sent " + jsonObject);
                    out.write(jsonObject.toString());
                }

                exchange.close();
                handler.setCurrentExchange(null);
            }
        }
        catch (IOException e)
        {
            log.error("Error handling HTTP exchange", e);
        }
    }

//    public void handleHttpExchange()
//    {
//        try
//        {
//            
//            Iterator<BaseHttpHandler> iterator = handlersQueued.iterator();
//            while (iterator.hasNext())
//            {
//                BaseHttpHandler handler = iterator.next();
//                if (handlersProcessed.contains(handler))
//                    continue;
//                
//                handlersProcessed.add(handler);
//                
//                HttpExchange exchange = handler.getCurrentExchange();
//                exchange.sendResponseHeaders(200, 0);
//                
//                try (OutputStreamWriter out = new OutputStreamWriter(exchange.getResponseBody()))
//                {
//                    JsonObject jsonObject = new JsonObject();
//                    jsonObject.add(handler.getName(), RuneLiteAPI.GSON.toJsonTree(handler.getData()));
//
//                    log.info("Sent " + jsonObject);
//                    out.write(jsonObject.toString());
//                }
//
//                exchange.close();
//                handler.setCurrentExchange(null);
//
//                iterator.remove();
//            }
//        }
//        catch (IOException e)
//        {
//            log.error("Error handling HTTP exchange", e);
//        }
//    }

    private void startHttpServer() throws Exception
    {
        inventoryHandler = new InventoryHandler();
        healthHandler = new HealthHandler();
        prayerHandler = new PrayerHandler();
        skillsHandler = new SkillsHandler();

        server = HttpServer.create(new InetSocketAddress(9420), 0);
        server.createContext("/inventory", inventoryHandler);
        server.createContext("/health", healthHandler);
        server.createContext("/prayer", prayerHandler);
        server.createContext("/skills", skillsHandler);
        server.setExecutor(Executors.newSingleThreadExecutor());
        server.start();

        log.info("GodlikeHttpPlugin HTTP server started!");
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
