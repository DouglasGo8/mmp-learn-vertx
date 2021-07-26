package com.udemy.learn.vertx.stock.broker.domain;

import io.vertx.core.json.JsonObject;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

/**
 * @author dougdb
 */
@Value
@Builder
public class Quote {

  Asset asset;
  BigDecimal bid;
  BigDecimal ask;
  BigDecimal volume;
  BigDecimal lastPrice;

  public JsonObject toJsonObject() {
    return JsonObject.mapFrom(this);
  }
}
