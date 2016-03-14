package com.pipasolutions.learning;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import io.vertx.ext.web.handler.sockjs.SockJSHandlerOptions;
import io.vertx.ext.web.handler.sockjs.SockJSSocket;
import io.vertx.ext.web.templ.HandlebarsTemplateEngine;
import io.vertx.ext.web.handler.StaticHandler;

public class HelloWorldVerticle extends AbstractVerticle {

	private HttpServer httpServer;
	private HandlebarsTemplateEngine engine;
	private Router router;
	//private int SocketI = 0;

	@Override
	public void start(Future fut) {
		// Create an HTTP server...
		httpServer = vertx.createHttpServer();
		engine = HandlebarsTemplateEngine.create();
		router = Router.router(vertx);

		SockJSHandlerOptions options = new SockJSHandlerOptions().setHeartbeatInterval(2000);
		SockJSHandler sockJSHandler = SockJSHandler.create(vertx, options);


		
		router.route("/myapp/*").handler(sockJSHandler);
		router.route("/Assets/*").handler(StaticHandler.create("Assets"));	

		JavaServer server = new JavaServer();
		
		router.route("/").handler(ctx -> {
			engine.render(ctx, "templates/index.hbs", res -> {
				System.out.println("Received HTTP request.");
				if (res.succeeded()) {
					ctx.response().end(res.result());
				} else {
					ctx.fail(res.cause());
				}
			});
		});
		
		
		router.route("/*").handler(ctx -> {
			//parse name out of URL
		});
		
		sockJSHandler.socketHandler(new Handler<SockJSSocket>(){
			private int SocketI = 0;
			 public void handle(SockJSSocket sockJSSocket){
		//		sockJSSocket -> {
			

			// Count the data type
			
				sockJSSocket.handler(msg -> {
					if (SocketI==0){
						SocketI++;
						server.newUser("" + msg);
						sockJSSocket.write(Buffer.buffer(server.getRoom()));
					} else{
						server.board.createMessage("name", ""+msg);
						sockJSSocket.write(Buffer.buffer("name", ""+msg));
						sockJSSocket.write(Buffer.buffer(server.getBoard()));
					}
				});
		
			 }
		});
			
		httpServer.requestHandler(router::accept);
		httpServer.listen(8080, res -> fut.complete());
	}

	@Override
	public void stop(Future fut) {
		httpServer.close(res -> fut.complete());
	}
}
