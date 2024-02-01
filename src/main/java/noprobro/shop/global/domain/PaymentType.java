package noprobro.shop.global.domain;

import lombok.Getter;

@Getter
public enum PaymentType {
  CREDIT_CARD("카드"), CASH("현금");

  private final String displayName;
  PaymentType(String displayName) {
    this.displayName = displayName;
  }
}