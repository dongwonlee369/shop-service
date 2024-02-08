package noprobro.shop.product.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import noprobro.shop.product.common.ProductSearchDto;
import noprobro.shop.product.domain.Product;
import noprobro.shop.product.domain.QProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;


public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

  private final JPAQueryFactory jpaQueryFactory;

  public ProductRepositoryCustomImpl(EntityManager em) {
    this.jpaQueryFactory = new JPAQueryFactory(em);
  }

  private BooleanExpression regDtsAfter(String searchDateType) {
    LocalDateTime localDateTime = LocalDateTime.now();

    if (Boolean.TRUE.equals(StringUtils.equals("all", searchDateType)) || searchDateType == null) {
      return null;
    } else if (Boolean.TRUE.equals(StringUtils.equals("1d", searchDateType))) {
      localDateTime = localDateTime.minusDays(1);
    } else if (Boolean.TRUE.equals(StringUtils.equals("1w", searchDateType))) {
      localDateTime = localDateTime.minusWeeks(1);
    } else if (Boolean.TRUE.equals(StringUtils.equals("1m", searchDateType))) {
      localDateTime = localDateTime.minusMonths(1);
    } else if (Boolean.TRUE.equals(StringUtils.equals("6m", searchDateType))) {
      localDateTime = localDateTime.minusMonths(6);
    }
    return QProduct.product.createTime.after(localDateTime);
  }

  private BooleanExpression searchByLike(String searchBy, String searchQuery) {
    if (Boolean.TRUE.equals(StringUtils.equals("productName", searchBy))) {
      return QProduct.product.name.like("%" + searchQuery + "%");
    }
    return null;
  }

  @Override
  public Page<Product> getAdminProductPage(ProductSearchDto productSearchDto, org.springframework.data.domain.Pageable pageable) {

    QueryResults<Product> results = jpaQueryFactory
        .selectFrom(QProduct.product)
        .where(regDtsAfter(productSearchDto.getSearchDateType()),
            searchByLike(productSearchDto.getSearchBy(), productSearchDto.getSearchQuery()))
        .orderBy(QProduct.product.id.desc())
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetchResults();

    List<Product> content = results.getResults();

    long total = results.getTotal();

    return new PageImpl<>(content, pageable, total);
  }
}
