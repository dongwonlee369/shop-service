package noprobro.shop.product.repository;

import noprobro.shop.product.domain.ProductImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImgRepository extends JpaRepository<ProductImg, Long> {
  List<ProductImg> findByProductIdOrderByIdAsc(Long productId);
}
