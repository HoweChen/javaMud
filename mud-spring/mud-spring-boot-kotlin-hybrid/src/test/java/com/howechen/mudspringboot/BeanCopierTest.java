package com.howechen.mudspringboot;

import com.howechen.mudspringboot.pojo.SampleDO;
import com.howechen.mudspringboot.pojo.SampleDTO;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanCopier;

public class BeanCopierTest {

  private static final Logger log = LoggerFactory.getLogger(BeanCopierTest.class);

  public static final BeanCopier DTO_2_DO =
      BeanCopier.create(SampleDTO.class, SampleDO.class, false);

  @Test
  public void beanCopierTest() {
    SampleDTO dto = new SampleDTO();
    dto.username = "yuhaochen";
    dto.password = "12345";
    SampleDO converted = new SampleDO();
    DTO_2_DO.copy(dto, converted, null);

    log.info(dto.toString());
    log.info(converted.toString());
  }
}
