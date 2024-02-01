package noprobro.shop.global.domain;

import lombok.Getter;

@Getter
public enum DeliveryStatus {
  BEFORE_DELIVERY("배송 전"), DELIVERY("배송 중"), DELIVERY_COMPLETED("배송 완료");

  private final String displayName;
  DeliveryStatus(String displayName) {
    this.displayName = displayName;
  }
}
