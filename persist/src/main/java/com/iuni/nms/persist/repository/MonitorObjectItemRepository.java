package com.iuni.nms.persist.repository;

import com.iuni.nms.persist.domain.MonitorObjectItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface MonitorObjectItemRepository extends JpaRepository<MonitorObjectItem, Long>, JpaSpecificationExecutor<MonitorObjectItem>, QueryDslPredicateExecutor<MonitorObjectItem> {
}
