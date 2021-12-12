package com.thinhvo.salesystem.service.order;

import com.thinhvo.salesystem.datamodel.ItemEntity;
import com.thinhvo.salesystem.datamodel.OrderEntity;
import com.thinhvo.salesystem.service.item.ItemService;
import com.thinhvo.salesystem.service.order.dto.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;

@Service
@Transactional
public class OrderService {

    private final ItemService itemService;

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(ItemService itemService, OrderRepository orderRepository) {
        this.itemService = itemService;
        this.orderRepository = orderRepository;
    }

    public OrderResponse createOrder(long itemId, int quantity) {
        ItemEntity item = itemService.findById(itemId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item with id: " + itemId + " not found"));

        int currentStock = item.getCurrentStock();
        if (currentStock < quantity) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not enough item to create order, current stock: " + currentStock);
        }

        item.setCurrentStock(currentStock - quantity);
        itemService.save(item);
        OrderEntity orderEntity = orderRepository.saveAndFlush(createOrder(item, quantity));
        return toOrderResponse(orderEntity);
    }

    private static OrderEntity createOrder(ItemEntity item, int quantity) {
        OrderEntity order = new OrderEntity();
        order.setCreatedTime(Instant.now());
        order.setItemId(item.getId());
        order.setQuantity(quantity);
        return order;
    }

    private static OrderResponse toOrderResponse(OrderEntity orderEntity) {
        OrderResponse response = new OrderResponse();
        response.setId(orderEntity.getId());
        response.setQuantity(orderEntity.getQuantity());
        response.setBoughtAt(orderEntity.getCreatedTime());
        response.setItemId(orderEntity.getItemId());
        return response;
    }
}
