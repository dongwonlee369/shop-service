package noprobro.shop.product.repository;

import noprobro.shop.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ProductRepository extends JpaRepository<Product, Long>,
    QuerydslPredicateExecutor<Product>, ProductRepositoryCustom {
}
