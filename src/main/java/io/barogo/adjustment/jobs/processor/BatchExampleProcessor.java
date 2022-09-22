package io.barogo.adjustment.jobs.processor;

import io.barogo.adjustment.model.entity.adjustment.mybatis.BatchExampleWriterDto;
import io.barogo.adjustment.model.entity.o2o.BaromoneyHistoryOfHubDTO;
import org.springframework.batch.item.ItemProcessor;

public class BatchExampleProcessor implements ItemProcessor<BaromoneyHistoryOfHubDTO, BatchExampleWriterDto> {

  @Override
  public BatchExampleWriterDto process(BaromoneyHistoryOfHubDTO item) throws Exception {

    return BatchExampleWriterDto.builder()
        .transactionNo(item.getBr_cash_no())
        .orderId(item.getOrd_no())
        .memo(item.getBr_cash_memo())
        .build();

  }
}
