package com.iuni.nms.persist.repository;

import com.iuni.nms.persist.domain.AlarmInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface AlarmInfoRepository extends JpaRepository<AlarmInfo, Long>, JpaSpecificationExecutor<AlarmInfo>, QueryDslPredicateExecutor<AlarmInfo> {
}
