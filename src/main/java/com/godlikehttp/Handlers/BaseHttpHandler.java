package com.godlikehttp.Handlers;

import com.godlikehttp.GodlikeHttpPlugin;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Setter
@Getter
@Slf4j
public abstract class BaseHttpHandler implements HttpHandler
{
    private HttpExchange currentExchange;


    @Override
    public void handle(HttpExchange exchange) throws IOException
    {
        if (!GodlikeHttpPlugin.instance.handlersProcessed.contains(this))
        {
            this.currentExchange = exchange;
            GodlikeHttpPlugin.instance.handlersProcessed.add(this);
            GodlikeHttpPlugin.instance.handlersQueued.add(this);
        }

//        exchange.sendResponseHeaders(200, 0);
//        try (OutputStreamWriter out = new OutputStreamWriter(exchange.getResponseBody()))
//        {
//            JsonObject jsonObject = new JsonObject();
//            jsonObject.add(getName(), RuneLiteAPI.GSON.toJsonTree(getData()));
//            
//            log.info("Sent " + jsonObject);
//            out.write(jsonObject.toString());
//        }
//        exchange.close();
    }

    public abstract String getName();

    public abstract Object getData();

//    public HttpExchange getHttpExchange()
//    {
//        return this.currentExchange;
//    }
}
