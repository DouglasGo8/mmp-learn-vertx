package com.udemy.learn.vertx.stock.broker.web.in;

import com.udemy.learn.vertx.stock.broker.domain.Asset;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.Router;

public class AssetsRestApi {

  public static void attach(final Router api) {
    //
    api.get("/assets").handler(context -> {
      final var resp = new JsonArray();
      //
      resp.add(new Asset("AAPL"))
              .add(new Asset("AMZN"))
              .add(new Asset("NFLX"))
              .add(new Asset("TSLA"))
              .add(new Asset("ORAC"));
      //
      context.response().setStatusCode(200).end(resp.toBuffer());
    });
  }
}
