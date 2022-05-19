package com.howechen.mudspringboot.mybatis.repository;

import com.howechen.mudspringboot.mybatis.entity.CustomerDO;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CustomerDORepository {

  long insert(CustomerDO customerDO);

  long update(CustomerDO customerDO);

  List<CustomerDO> findAll();

  Optional<CustomerDO> findById(long l);

  Optional<List<CustomerDO>> findByLastName(String bauer);
}
