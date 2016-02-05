package com.iuni.nms.persist.repository;

import com.iuni.nms.persist.domain.System;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface SystemRepository extends JpaRepository<System, Long>, JpaSpecificationExecutor<System>, QueryDslPredicateExecutor<System> {
}
