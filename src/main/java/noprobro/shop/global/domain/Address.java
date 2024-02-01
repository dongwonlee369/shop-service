package noprobro.shop.global.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Address {

  @Column(nullable = false)
  private String addressName;

  @Column(nullable = false)
  private String addressAddress;
}
