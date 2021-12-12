package com.thinhvo.salesystem.service.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
public class ItemResponse {

    @JsonProperty
    private long id;

    @JsonProperty("current_stock_value")
    private int currentStock;

    @JsonProperty("placed_at")
    private Instant placedAt;

    @JsonProperty("selling_price")
    private BigDecimal sellPrice;
}
