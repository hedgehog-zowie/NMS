package com.iuni.nms.persist.repository;

import com.iuni.nms.persist.domain.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface ModuleRepository extends JpaRepository<Module, Long>, JpaSpecificationExecutor<Module>, QueryDslPredicateExecutor<Module> {
}
