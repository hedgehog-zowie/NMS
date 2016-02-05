package com.iuni.nms.persist.repository;

import com.iuni.nms.persist.domain.AlarmGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface AlarmGroupRepository extends JpaRepository<AlarmGroup, Long>, JpaSpecificationExecutor<AlarmGroup>, QueryDslPredicateExecutor<AlarmGroup> {
}
