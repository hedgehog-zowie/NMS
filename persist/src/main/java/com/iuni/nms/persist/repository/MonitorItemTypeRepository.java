package com.iuni.nms.persist.repository;

import com.iuni.nms.persist.domain.MonitorItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface MonitorItemTypeRepository extends JpaRepository<MonitorItemType, Long>, JpaSpecificationExecutor<MonitorItemType>, QueryDslPredicateExecutor<MonitorItemType> {
}
