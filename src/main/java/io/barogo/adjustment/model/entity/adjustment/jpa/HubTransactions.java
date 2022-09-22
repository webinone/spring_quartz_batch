//package io.barogo.adjustment.model.entity.adjustment.jpa;
//
//import java.math.BigDecimal;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import lombok.AccessLevel;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class HubTransactions {
//
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  private Long seq;
//
//  @Column(name = "transaction_no", unique = true)
//  private Long transactionNo;
//
//  @Column(name = "distributor_id", length = 5)
//  private String distributorId;
//
//  @Column(name = "hub_id", length = 5)
//  private String hubId;
//
//  @Column(name = "order_id")
//  private Long orderId;
//
//  @Column(name = "amount")
//  private BigDecimal amount;
//
//  @Column(name = "tax_amount")
//  private BigDecimal taxAmount;
//
//  @Column(name = "total_amount")
//  private BigDecimal totalAmount;
//
//}
