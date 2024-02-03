package noprobro.shop.order.domain;

import jakarta.persistence.*;
import lombok.*;
import noprobro.shop.global.domain.BaseEntity;
import noprobro.shop.product.domain.Product;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString
public class OrderDetail extends BaseEntity {

  @Column(nullable = false)
  private String count;

  @Column(nullable = false)
  private String price;

  @ToString.Exclude
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  private Order order;

  @ToString.Exclude
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  private Product product;
}
