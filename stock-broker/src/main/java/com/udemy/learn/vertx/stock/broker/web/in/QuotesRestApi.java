package com.udemy.learn.vertx.stock.broker.web.in;

import com.udemy.learn.vertx.stock.broker.domain.Asset;
import com.udemy.learn.vertx.stock.broker.domain.Quote;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author dougdb
 */
@Log4j2
public class QuotesRestApi {

  public static void attach(final Router api) {
    api.get("/quotes/:asset").handler(ctx -> {
      var param = ctx.pathParam("asset");
      log.info("Asset param {}", param);
      //
      final var quote = giveThatQuote(param);
      final var resp = quote.toJsonObject();

      //log.info(resp.toBuffer());

      ctx.response().end(resp.toBuffer());
    });
  }

  private static Quote giveThatQuote(final String param) {
    return Quote.builder().asset(new Asset(param))
            .volume(askForRandom())
            .ask(askForRandom())
            .bid(askForRandom())
            .lastPrice(askForRandom())
            .build();
  }

  private static BigDecimal askForRandom() {
    return BigDecimal.valueOf(ThreadLocalRandom.current().nextInt(1, 100));
  }
}
