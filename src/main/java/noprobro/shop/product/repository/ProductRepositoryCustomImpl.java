package noprobro.shop.product.repository;

import com.querydsl.core.QueryFactory;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import noprobro.shop.product.common.HomeProductDto;
import noprobro.shop.product.common.ProductSearchDto;
import noprobro.shop.product.common.QHomeProductDto;
import noprobro.shop.product.domain.Product;
import noprobro.shop.product.domain.QProduct;
import noprobro.shop.product.domain.QProductImg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

  private BooleanExpression productNameLike(String searchQuery) {
    return StringUtils.isEmpty(searchQuery) ? null
        : QProduct.product.name.like("%" + searchQuery + "%");
  }

  @Override
  public Page<HomeProductDto> getHomeProductPage(ProductSearchDto productSearchDto, Pageable pageable) {
    QProduct product = QProduct.product;
    QProductImg productImg = QProductImg.productImg;

    QueryResults<HomeProductDto> results = jpaQueryFactory
        .select(
            new QHomeProductDto(
                product.name,
                product.price,
                product.category,
                product.detail)
        )
        .from(productImg)
        .join(productImg.product, product)
        .where(productImg.repImgYn.eq("Y"))
        .where(productNameLike((productSearchDto.getSearchQuery())))
        .orderBy(product.id.desc())
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetchResults();

    List<HomeProductDto> content = results.getResults();
    long total = results.getTotal();
    return new PageImpl<>(content, pageable, total);
  }
}
