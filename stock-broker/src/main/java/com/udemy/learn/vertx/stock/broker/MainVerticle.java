package com.udemy.learn.vertx.stock.broker;

import com.udemy.learn.vertx.stock.broker.web.in.AssetsRestApi;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import lombok.extern.log4j.Log4j2;

/**
 * @author dougdb
 */
@Log4j2
public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> promise) {

    final var api = Router.router(super.vertx);
    api.route().failureHandler(err -> {
      if (err.response().ended()) {
        // Ignore
        return;
      }
      log.error("Route Error: ", err.failure());
      err.response().setStatusCode(500).end(new JsonObject().put("message", "Something went wrong").toBuffer());
    });

    AssetsRestApi.attach(api);

    super.vertx.createHttpServer().requestHandler(api)
            .exceptionHandler(err -> log.error("HTTP Server Error: ", err))
            .listen(8888, r -> {
              if (r.succeeded()) {
                promise.complete();
                log.info("HTTP Server start on port 8888");
              } else {
                promise.fail(r.cause());
              }

            });


  }

}
