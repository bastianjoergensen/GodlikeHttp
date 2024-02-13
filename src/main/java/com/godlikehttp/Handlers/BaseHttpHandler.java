package com.godlikehttp.Handlers;

import com.godlikehttp.GodlikeHttpPlugin;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.http.api.RuneLiteAPI;

import java.io.IOException;
import java.io.OutputStreamWriter;

@Setter
@Getter
@Slf4j
public abstract class BaseHttpHandler implements HttpHandler
{
    private HttpExchange currentExchange;
    private volatile boolean canTick;


    @Override
    public void handle(HttpExchange exchange) throws IOException
    {
        if (GodlikeHttpPlugin.instance.handlersQueued.contains(this))
        {
            exchange.sendResponseHeaders(200, 0);
            exchange.close();
            return;
        }

        GodlikeHttpPlugin.instance.handlersQueued.add(this);
        exchange.sendResponseHeaders(200, 0);

        while (!canTick)
            Thread.onSpinWait();

        canTick = false;
        log.info("Handling exchange: " + exchange.getRequestURI().toString());

        try (OutputStreamWriter out = new OutputStreamWriter(exchange.getResponseBody()))
        {
            JsonObject jsonObject = new JsonObject();
            jsonObject.add(getName(), RuneLiteAPI.GSON.toJsonTree(getData()));

            out.write(jsonObject.toString());
        }
        finally
        {
            exchange.close();
        }
    }

    public abstract String getName();

    public abstract Object getData();
}
