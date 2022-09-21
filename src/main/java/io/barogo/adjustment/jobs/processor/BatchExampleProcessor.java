package io.barogo.adjustment.jobs.processor;

import io.barogo.adjustment.model.entity.adjustment.mybatis.BatchExampleReader;
import io.barogo.adjustment.model.entity.adjustment.mybatis.BatchExampleWriter;
import java.util.List;
import org.springframework.batch.item.ItemProcessor;

public class BatchExampleProcessor implements ItemProcessor<BatchExampleReader, BatchExampleWriter> {

  @Override
  public BatchExampleWriter process(BatchExampleReader item) throws Exception {

    System.out.println(">>>>> BatchExampleProcessor process !!! ");
    System.out.println(item.toString());

    return BatchExampleWriter.builder()
        .id(item.getId() + "-id-writer")
        .name(item.getName() + "-name-writer").build();
  }
}
