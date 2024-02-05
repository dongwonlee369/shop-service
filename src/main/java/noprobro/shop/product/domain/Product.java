package noprobro.shop.product.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;
import noprobro.shop.cart.domain.CartDetail;
import noprobro.shop.global.domain.BaseEntity;
import noprobro.shop.order.domain.OrderDetail;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString
public class Product extends BaseEntity {

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String price;

  @Column(nullable = false)
  private String category;

  @Column(nullable = false)
  private String detail;

  @ToString.Exclude
  @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
  private List<OrderDetail> orderDetails = new ArrayList<>();

  @ToString.Exclude
  @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
  private List<CartDetail> cartDetails = new ArrayList<>();

  @ToString.Exclude
  @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
  private List<ProductImg> productImgs = new ArrayList<>();
}
