package noprobro.shop.global.domain;

import lombok.Getter;

@Getter
public enum Gender {
  MALE("남자"), FEMALE("여자");

  private final String displayName;
  Gender(String displayName) {
    this.displayName = displayName;
  }
}
