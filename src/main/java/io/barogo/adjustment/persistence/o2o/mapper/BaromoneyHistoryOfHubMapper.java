package io.barogo.adjustment.persistence.o2o.mapper;

import io.barogo.adjustment.model.entity.o2o.BaromoneyHistoryOfHubDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaromoneyHistoryOfHubMapper {

  BaromoneyHistoryOfHubDTO readHistory();

}
