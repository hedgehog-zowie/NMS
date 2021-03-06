package com.iuni.nms.persist.repository;

import com.iuni.nms.persist.domain.MonitorObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface MonitorObjectRepository extends JpaRepository<MonitorObject, Long>, JpaSpecificationExecutor<MonitorObject>, QueryDslPredicateExecutor<MonitorObject> {

    MonitorObject findByCode(String code);

}
