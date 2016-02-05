package com.iuni.nms.persist.repository;

import com.iuni.nms.persist.domain.MonitorItemProps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface MonitorItemPropsRepository extends JpaRepository<MonitorItemProps, Long>, JpaSpecificationExecutor<MonitorItemProps>, QueryDslPredicateExecutor<MonitorItemProps> {

    MonitorItemProps findByCode(String code);

}
