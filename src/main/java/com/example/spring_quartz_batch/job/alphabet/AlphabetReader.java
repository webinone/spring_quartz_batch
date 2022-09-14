package com.example.spring_quartz_batch.job.alphabet;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

@Slf4j
public class AlphabetReader implements ItemReader<String> {

  private int idx;
  private List<String> alphabets;

  public AlphabetReader() {
    idx = 0;
    init();
  }

  private void init() {
    alphabets = new ArrayList<String>();
    alphabets.add("a");
    alphabets.add("b");
    alphabets.add("c");
    alphabets.add("d");
    alphabets.add("e");
    alphabets.add("f");
    alphabets.add("g");
    alphabets.add("h");
    alphabets.add("i");
    alphabets.add("j");
    alphabets.add("k");
    alphabets.add(UUID.randomUUID().toString());
  }

  @Override
  public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
    String alphabet = null;
    if (idx < alphabets.size()) {
      alphabet = alphabets.get(idx);
      idx++;
    }
    log.info("Read {} - {}", idx, alphabet);
    return alphabet;
  }
}
