package com.udemy.learn.vertx.promises.futures;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import lombok.extern.log4j.Log4j2;

/**
 * Promise - Used to write an eventual value
 * Future - Used to read the value from the Promise when it is available
 */
@Log4j2
public class MainVerticle extends AbstractVerticle {


  //public static void main(String... args) {
  //var vertx = Vertx.vertx();
  //vertx.deployVerticle(new MainVerticle());
  //vertx.deployVerticle(new FuturePromiseSample());
  //}

  //static class FuturePromiseSample extends AbstractVerticle {
  @Override
  public void start(final Promise<Void> start) throws Exception {
    var promise = Promise.<String>promise();
    /*super.vertx.setTimer(500, id -> {
      promise.complete("Success");
      log.info("Complete Now");
    });

    promise.future().onComplete(r -> {
      log.info(r.result());
    });*/

    super.vertx.setTimer(500, id -> {
      promise.fail(new RuntimeException("Fail"));
      log.info("Success?");
    });
    promise.future().onComplete(r -> {
      log.info(r.result());
    }).onFailure(r -> {
      log.error(r.getMessage());
    });
    start.complete();
  }
  //}
}
