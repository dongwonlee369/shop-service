package noprobro.shop.product.common;

import lombok.Getter;
import lombok.Setter;
import noprobro.shop.product.domain.ProductImg;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class ProductImgDto {

  private Long id;
  private String imgName;
  private String oriImgName;
  private String imgUrl;
  private String repImgYn;

  private static ModelMapper modelMapper = new ModelMapper();

  public static ProductImgDto of(ProductImg productImg) {
    return modelMapper.map(productImg, ProductImgDto.class);
  }
}
