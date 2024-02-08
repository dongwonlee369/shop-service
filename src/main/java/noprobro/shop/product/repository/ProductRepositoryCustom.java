package noprobro.shop.product.repository;

import noprobro.shop.product.common.ProductSearchDto;
import noprobro.shop.product.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductRepositoryCustom {
  Page<Product> getAdminProductPage(ProductSearchDto productSearchDto, Pageable pageable);
}
