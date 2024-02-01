package noprobro.shop.global.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class CreditCard {

  @Column(nullable = false)
  private String creditCardNumber;

  @Column(nullable = false)
  private String creditCardExpiryDate;

  @Column(nullable = false)
  private String creditCardCvc;
}
