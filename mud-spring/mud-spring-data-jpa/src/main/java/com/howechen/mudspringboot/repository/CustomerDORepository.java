package com.howechen.mudspringboot.repository;

import com.howechen.mudspringboot.entity.CustomerDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerDORepository extends JpaRepository<CustomerDO, Long> {
  Optional<List<CustomerDO>> findByLastName(String lastName);
}
