package com.iuni.nms.persist.repository;

import com.iuni.nms.persist.domain.AlarmGroupPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface AlarmGroupPersonRepository extends JpaRepository<AlarmGroupPerson, Long>, JpaSpecificationExecutor<AlarmGroupPerson>, QueryDslPredicateExecutor<AlarmGroupPerson> {
}
