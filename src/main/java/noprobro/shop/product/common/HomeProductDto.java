package noprobro.shop.product.common;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HomeProductDto {

  private String name;
  private String price;
  private String category;
  private String detail;

  @QueryProjection
  public HomeProductDto(String name, String price, String category, String detail) {
    this.name = name;
    this.price = price;
    this.category = category;
    this.detail = detail;
  }
}
