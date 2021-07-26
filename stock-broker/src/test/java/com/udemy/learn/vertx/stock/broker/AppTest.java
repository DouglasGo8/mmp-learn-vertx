package com.udemy.learn.vertx.stock.broker;


import io.vertx.core.Vertx;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import lombok.extern.log4j.Log4j2;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * @author dougdb
 */
@Log4j2
@ExtendWith(VertxExtension.class)
public class AppTest {

  @BeforeEach
  void deployThatVerticles(final Vertx vertx, final VertxTestContext testContext) {
    vertx.deployVerticle(new MainVerticle(), testContext.succeeding(id -> testContext.completeNow()));
  }

  @Test
  void deployedMainVerticle(final Vertx vertx, final VertxTestContext testContext) {
    var client = WebClient.create(vertx, new WebClientOptions().setDefaultPort(8888));
    var respFromApi = "{\"symbol\":\"AAPL\"},{\"symbol\":\"AMZN\"},{\"symbol\":\"NFLX\"},{\"symbol\":\"TSLA\"},{\"symbol\":\"ORAC\"}";
    //
    client.get("/assets").send()
            .onComplete(testContext.succeeding(resp ->{
              var json = resp.bodyAsJsonArray();
              log.info("Response {}", json);
              assertEquals(respFromApi, json.encode());
              assertEquals(200, resp.statusCode());
              //
              testContext.completeNow();
            }));
  }
}
