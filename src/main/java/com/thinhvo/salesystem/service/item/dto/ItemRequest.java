package com.thinhvo.salesystem.service.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Getter
@Setter
public class ItemRequest {

    @JsonProperty("total_stock_value")
    @Min(value = 0, message = "Field: total_stock_value must be greater than 0")
    private int totalStock;

    @JsonProperty("selling_price")
    @DecimalMin(value = "1.0", inclusive = false, message = "Field: selling_price must be greater than 1.0")
    @Digits(integer = 9, fraction = 2, message = "Field: selling_price must be a decimal with pattern: #.##")
    private BigDecimal sellPrice;
}
