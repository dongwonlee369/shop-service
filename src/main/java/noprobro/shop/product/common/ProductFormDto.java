package noprobro.shop.product.common;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import noprobro.shop.product.domain.Product;
import noprobro.shop.product.domain.ProductImg;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductFormDto {

  private Long id;

  @NotBlank(message = "상품명은 필수 입력 값입니다.")
  private String name;

  @NotBlank(message = "카테고리는 필수 입력 값입니다.")
  private String category;

  @NotBlank(message = "가격은 필수 입력 값입니다.")
  private String price;

  @NotBlank(message = "상세설명은 필수 입력 값입니다.")
  private String detail;

  private List<ProductImgDto> productImgDtos = new ArrayList<>();

  private List<ProductImg> productImgs = new ArrayList<>();

  private static ModelMapper modelMapper = new ModelMapper();

  public Product createProduct() {
    return modelMapper.map(this, Product.class);
  }

  public static ProductFormDto of(Product product) {
    return modelMapper.map(product, ProductFormDto.class);
  }
}
