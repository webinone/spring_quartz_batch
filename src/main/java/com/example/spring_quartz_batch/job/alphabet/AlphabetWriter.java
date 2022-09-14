package com.example.spring_quartz_batch.job.alphabet;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

@Slf4j
public class AlphabetWriter implements ItemWriter<String> {

  @Override
  public void write(List<? extends String> items) throws Exception {
    for (String item : items) {
      log.info("write - {}", item);
    }
  }
}
