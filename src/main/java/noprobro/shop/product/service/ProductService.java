package noprobro.shop.product.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import noprobro.shop.product.common.ProductFormDto;
import noprobro.shop.product.common.ProductImgDto;
import noprobro.shop.product.domain.Product;
import noprobro.shop.product.domain.ProductImg;
import noprobro.shop.product.repository.ProductImgRepository;
import noprobro.shop.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final ProductImgService productImgService;
  private final ProductImgRepository productImgRepository;

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

  @Transactional
  public ProductFormDto getProductDtl(Long productId) {
    List<ProductImg> productImgs = productImgRepository.findByProductIdOrderByIdAsc(productId);
    List<ProductImgDto> productImgDtos = new ArrayList<>();

    for (ProductImg productImg : productImgs) {
      ProductImgDto productImgDto = ProductImgDto.of(productImg);
      productImgDtos.add(productImgDto);
    }

    Product product = productRepository.findById(productId)
        .orElseThrow(EntityNotFoundException::new);

    ProductFormDto productFormDto = ProductFormDto.of(product);
    productFormDto.setProductImgDtos(productImgDtos);
    return productFormDto;
  }

  @Transactional
  public void updateProduct(ProductFormDto productFormDto,
                            List<MultipartFile> productImgFileList) throws Exception {

    Product product = productRepository.findById(productFormDto.getId())
        .orElseThrow(EntityNotFoundException::new);
    product.updateProduct(productFormDto);

    List<Long> productImgIds = productFormDto.getProductImgIds();
    for (int i = 0; i < productImgFileList.size(); i++) {
      productImgService.updateProductImg(productImgIds.get(i), productImgFileList.get(i));
    }
  }
}
