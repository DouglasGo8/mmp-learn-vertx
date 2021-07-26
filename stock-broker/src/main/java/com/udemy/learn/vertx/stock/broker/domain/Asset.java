package com.udemy.learn.vertx.stock.broker.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Asset {
  private final String symbol;
}
