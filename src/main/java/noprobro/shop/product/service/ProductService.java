package noprobro.shop.product.service;

import lombok.RequiredArgsConstructor;
import noprobro.shop.product.common.ProductFormDto;
import noprobro.shop.product.domain.Product;
import noprobro.shop.product.domain.ProductImg;
import noprobro.shop.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final ProductImgService productImgService;

  public Long saveProduct(ProductFormDto productFormDto, List<MultipartFile> productImgFileList) throws Exception {
    Product product = productFormDto.createProduct();
    productRepository.save(product);

    for (int i = 0; i < productImgFileList.size(); i++) {
      ProductImg productImg = ProductImg.createProductImg();
      productImg.setProduct(product);
      if (i == 0) {
        productImg.setRepImgYn("Y");
      } else {
        productImg.setRepImgYn("N");
      }
      productImgService.saveProductImg(productImg, productImgFileList.get(i));
    }
    return product.getId();
  }
}
