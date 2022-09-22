package io.barogo.adjustment.model.entity.o2o;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaromoneyHistoryOfHubDTO {

  @JsonProperty("id")
  private Long br_cash_no;

  @JsonProperty("distributorId")
  private String hd_code;

  @JsonProperty("hubId")
  private String br_code;

  @JsonProperty("type")
  private String br_cash_type_cd;

  @JsonProperty("transactionBaromoney")
  private BigDecimal add_cash;

  @JsonProperty("balanceBaromoney")
  private BigDecimal br_cash;

  @JsonProperty("memo")
  private String br_cash_memo;

  @JsonProperty("orderId")
  private Long ord_no;

  @JsonProperty("createdAt")
  private ZonedDateTime in_date;

  @JsonProperty("createdBy")
  private String in_usr_id;
}
