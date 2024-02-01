package noprobro.shop.cart.domain;

import jakarta.persistence.*;
import lombok.*;
import noprobro.shop.global.domain.BaseEntity;
import noprobro.shop.global.domain.DeliveryStatus;
import noprobro.shop.global.domain.PaymentType;
import noprobro.shop.member.domain.Member;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString
public class Cart extends BaseEntity {

  @Column(nullable = false)
  private String cartAmount;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private PaymentType paymentType;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private DeliveryStatus deliveryStatus;

  @ToString.Exclude
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  private Member member;

  @ToString.Exclude
  @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY)
  private List<CartDetail> cartDetails = new ArrayList<>();
}
