package com.thinhvo.salesystem.controller;

import com.thinhvo.salesystem.service.item.ItemService;
import com.thinhvo.salesystem.service.item.dto.ItemRequest;
import com.thinhvo.salesystem.service.item.dto.ItemResponse;
import com.thinhvo.salesystem.service.item.dto.ListItemsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ListItemsResponse getItems(@RequestParam @Min(1) int page, @RequestParam @Min(1) int limit) {
        return itemService.findAllPagination(page, limit);
    }

    @PostMapping
    public ItemResponse createItem(@Valid @RequestBody ItemRequest itemRequest) {
        return itemService.create(itemRequest);
    }
}
