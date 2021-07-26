package com.udemy.learn.vertx.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import lombok.extern.log4j.Log4j2;

/**
 * @author dougdb
 */
@Log4j2
public class MainVerticle extends AbstractVerticle {

  // Native Apps
  /*public static void main(String... args) {
    Vertx.vertx().deployVerticle(new MainVerticle();
  }*/

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    vertx.createHttpServer().requestHandler(req -> {
      req.response()
        .putHeader("content-type", "text/plain")
        .end("Hello from Vert.x!");
    }).listen(8888, http -> {
      if (http.succeeded()) {
        startPromise.complete();
        log.info("HTTP server started on port 8888");
      } else {
        startPromise.fail(http.cause());
      }
    });
  }
}
