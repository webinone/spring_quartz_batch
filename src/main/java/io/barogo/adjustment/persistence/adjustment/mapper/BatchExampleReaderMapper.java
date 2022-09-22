package io.barogo.adjustment.persistence.adjustment.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BatchExampleReaderMapper {

  BatchExampleReader readAll();

  BatchExampleReader readPaging();

}
