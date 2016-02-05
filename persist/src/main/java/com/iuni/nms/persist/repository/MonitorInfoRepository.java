package com.iuni.nms.persist.repository;

import com.iuni.nms.persist.domain.MonitorInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface MonitorInfoRepository extends JpaRepository<MonitorInfo, Long>, JpaSpecificationExecutor<MonitorInfo>, QueryDslPredicateExecutor<MonitorInfo> {
}
