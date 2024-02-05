package noprobro.shop.product.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString
public class ProductImg {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String imgName;

  private String oriImgName;

  private String imgUrl;

  private String repImgYn;

  @ToString.Exclude
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  private Product product;

  public void updateProductImg(String imgName, String oriImgName, String imgUrl) {
    this.imgName = imgName;
    this.oriImgName = oriImgName;
    this.imgUrl = imgUrl;
  }
}
