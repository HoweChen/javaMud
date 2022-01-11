package com.howechen.mudspringboot.sentinel.repository;

import com.howechen.mudspringboot.sentinel.pojo.RuleDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** @author yuhaochen */
@Repository
public interface RuleDAO extends JpaRepository<RuleDO, Long> {}
