package com.thinhvo.salesystem.service.order.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@Getter
@Setter
public class OrderRequest {

    @Min(value = 1, message = "Field: quantity must be greater than or equal to 1")
    protected int quantity;
}
