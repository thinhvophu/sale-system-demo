/*
 * Author : AdNovum Informatik AG
 */

package com.thinhvo.salesystem.service.item;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import com.thinhvo.salesystem.datamodel.ItemEntity;
import com.thinhvo.salesystem.service.item.dto.ItemRequest;
import com.thinhvo.salesystem.service.item.dto.ItemResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ItemServiceTest {

	private AutoCloseable autoCloseable;

	@InjectMocks
	private ItemService itemService;

	@Mock
	private ItemRepository itemRepository;

	@Captor
	ArgumentCaptor<ItemEntity> itemEntityArgumentCaptor;

	@BeforeEach
	void setUp() {
		autoCloseable = MockitoAnnotations.openMocks(this);
	}

	@AfterEach
	void release() throws Exception {
		autoCloseable.close();
	}

	@Test
	public void testFindById_whenNoDataFromRepository_shouldReturnEmptyResult() {
		long itemId = 1L;
		when(itemRepository.findById(itemId)).thenReturn(Optional.empty());
		assertTrue(itemService.findById(itemId).isEmpty());
	}

	@Test
	public void testFindById_whenReceiveDataFromRepository_shouldReturnCorrectData() {
		long itemId = 1L;
		ItemEntity expectedEntity = new ItemEntity();
		expectedEntity.setId(itemId);

		when(itemRepository.findById(itemId)).thenReturn(Optional.of(expectedEntity));
		ItemEntity actualEntity = itemService.findById(itemId).get();

		assertThat(actualEntity)
				.usingRecursiveComparison()
				.isEqualTo(expectedEntity);
	}

	@Test
	public void testCreate_whenCreateSucceed_shouldMapEntityToResponse() {
		long itemId = 1L;
		ItemRequest itemRequest = new ItemRequest();
		itemRequest.setSellPrice(new BigDecimal(100));
		itemRequest.setTotalStock(10);

		when(itemRepository.saveAndFlush(any(ItemEntity.class)))
				.then(invocation -> {
					ItemEntity itemEntity = invocation.getArgument(0);
					itemEntity.setId(itemId); // simulate the generated id from database
					return itemEntity;
				});

		ItemResponse actual = itemService.create(itemRequest);

		verify(itemRepository, times(1)).saveAndFlush(itemEntityArgumentCaptor.capture());

		assertEquals(itemId, actual.getId());
		assertEquals(itemRequest.getSellPrice(), actual.getSellPrice());
		assertEquals(itemRequest.getTotalStock(), actual.getCurrentStock());
		assertEquals(actual.getPlacedAt().truncatedTo(ChronoUnit.MINUTES).getEpochSecond(),
				actual.getPlacedAt().truncatedTo(ChronoUnit.MINUTES).getEpochSecond());
	}
}
