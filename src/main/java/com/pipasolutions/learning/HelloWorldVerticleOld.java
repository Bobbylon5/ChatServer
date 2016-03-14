package com.pipasolutions.learning;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import io.vertx.ext.web.handler.sockjs.SockJSHandlerOptions;
import io.vertx.ext.web.templ.HandlebarsTemplateEngine;


public class HelloWorldVerticleOld extends AbstractVerticle {

  private HttpServer httpServer;
  private HandlebarsTemplateEngine engine;
  private Router router;

  @Override
  public void start(Future fut) {
    // Create an HTTP server...
    httpServer = vertx.createHttpServer();
    engine = HandlebarsTemplateEngine.create();
    router = Router.router(vertx);


    
    router.get().handler(ctx -> {
        ctx.put("name", "Vert.x Web");

        engine.render(ctx, "templates/index.hbs", res -> {
            System.out.println("Received HTTP request.");
            if( res.succeeded()) { 
                ctx.response().end(res.result());
            } else {
                ctx.fail(res.cause());
            }
        });    
    });

    httpServer.requestHandler(router::accept);
    httpServer.listen(8080, res -> fut.complete());
    

  }

  @Override
  public void stop(Future fut) {
    httpServer.close( res -> fut.complete());
  }
}