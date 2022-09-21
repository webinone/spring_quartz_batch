package io.barogo.adjustment.model.entity.adjustment.mybatis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 단순 테스트를 위한 작업 dto 추후, 삭제 예정
 * */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BatchExampleWriter {

  private Long seq;

  private String id ;

  private String name;


}
