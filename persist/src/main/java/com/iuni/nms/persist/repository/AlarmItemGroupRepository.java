package com.iuni.nms.persist.repository;

import com.iuni.nms.persist.domain.AlarmItemGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface AlarmItemGroupRepository extends JpaRepository<AlarmItemGroup, Long>, JpaSpecificationExecutor<AlarmItemGroup>, QueryDslPredicateExecutor<AlarmItemGroup> {
}
