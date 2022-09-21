package io.barogo.adjustment.persistence.adjustment.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BatchExampleWriterMapper {

  void createShedLock(BatchExampleWriterMapper batchExampleWriterMapper);

}
