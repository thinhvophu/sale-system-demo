package com.thinhvo.salesystem.service.order;

import com.thinhvo.salesystem.datamodel.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}
