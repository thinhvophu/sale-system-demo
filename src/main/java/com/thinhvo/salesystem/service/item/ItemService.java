package com.thinhvo.salesystem.service.item;

import com.thinhvo.salesystem.datamodel.ItemEntity;
import com.thinhvo.salesystem.service.item.dto.ItemRequest;
import com.thinhvo.salesystem.service.item.dto.ItemResponse;
import com.thinhvo.salesystem.service.item.dto.ListItemsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Optional<ItemEntity> findById(long itemId) {
        return itemRepository.findById(itemId);
    }

    public ListItemsResponse findAllPagination(int page, int limit) {
        PageRequest pageRequest = PageRequest.of(page - 1, limit, Sort.by("createdTime").descending());
        Page<ItemEntity> pageItems = itemRepository.findAll(pageRequest);
        return toListItemResponse(pageItems);
    }

    public ItemEntity save(ItemEntity item) {
        return itemRepository.save(item);
    }

    public ItemResponse create(ItemRequest itemRequest) {
        ItemEntity itemEntity = itemRepository.saveAndFlush(toItemEntity(itemRequest));
        return toItemResponse(itemEntity);
    }

    private static ItemEntity toItemEntity(ItemRequest itemRequest) {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setCreatedTime(Instant.now());
        itemEntity.setCurrentStock(itemRequest.getTotalStock());
        itemEntity.setPrice(itemRequest.getSellPrice());
        return itemEntity;
    }

    private static ItemResponse toItemResponse(ItemEntity itemEntity) {
        ItemResponse itemResponse = new ItemResponse();
        itemResponse.setCurrentStock(itemEntity.getCurrentStock());
        itemResponse.setId(itemEntity.getId());
        itemResponse.setPlacedAt(itemEntity.getCreatedTime());
        itemResponse.setSellPrice(itemEntity.getPrice());
        return itemResponse;
    }

    private ListItemsResponse toListItemResponse(Page<ItemEntity> pageItems) {
        ListItemsResponse response = new ListItemsResponse();
        response.setPage(pageItems.getPageable().getPageNumber() + 1);
        response.setLimit(pageItems.getSize());
        response.setTotalItems(pageItems.getTotalElements());
        response.setData(pageItems.get().map(ItemService::toItemResponse).collect(Collectors.toList()));
        return response;
    }
}
