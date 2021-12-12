package com.thinhvo.salesystem.service.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class OrderResponse extends OrderRequest {

    private long id;

    @JsonProperty("item_id")
    private long itemId;

    private Instant boughtAt;
}
