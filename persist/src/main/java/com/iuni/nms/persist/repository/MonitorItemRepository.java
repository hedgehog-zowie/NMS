package com.iuni.nms.persist.repository;

import com.iuni.nms.persist.domain.MonitorItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface MonitorItemRepository extends JpaRepository<MonitorItem, Long>, JpaSpecificationExecutor<MonitorItem>, QueryDslPredicateExecutor<MonitorItem> {

    MonitorItem findByCode(String code);

}
