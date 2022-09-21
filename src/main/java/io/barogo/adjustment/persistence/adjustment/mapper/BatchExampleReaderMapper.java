package io.barogo.adjustment.persistence.adjustment.mapper;

import io.barogo.adjustment.model.entity.adjustment.mybatis.BatchExampleReader;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BatchExampleReaderMapper {

  BatchExampleReader readAll();

}
