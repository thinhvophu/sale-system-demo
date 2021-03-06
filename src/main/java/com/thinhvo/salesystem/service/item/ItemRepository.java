package com.thinhvo.salesystem.service.item;

import com.thinhvo.salesystem.datamodel.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
}
