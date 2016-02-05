package com.iuni.nms.persist.repository;

import com.iuni.nms.persist.domain.MonitorObjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface MonitorObjectTypeRepository extends JpaRepository<MonitorObjectType, Long>, JpaSpecificationExecutor<MonitorObjectType>, QueryDslPredicateExecutor<MonitorObjectType> {
}
