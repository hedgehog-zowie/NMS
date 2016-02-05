package com.iuni.nms.persist.repository;

import com.iuni.nms.persist.domain.AlarmPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface AlarmPersonRepository extends JpaRepository<AlarmPerson, Long>, JpaSpecificationExecutor<AlarmPerson>, QueryDslPredicateExecutor<AlarmPerson> {
}
