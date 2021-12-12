package com.thinhvo.salesystem.service.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListItemsResponse {

    private List<ItemResponse> data;

    private int page;

    private int limit;

    @JsonProperty("total_items")
    private long totalItems;
}
