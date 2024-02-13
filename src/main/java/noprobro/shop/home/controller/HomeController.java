package noprobro.shop.home.controller;

import lombok.RequiredArgsConstructor;
import noprobro.shop.product.common.HomeProductDto;
import noprobro.shop.product.common.ProductSearchDto;
import noprobro.shop.product.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

  private final ProductService productService;

  @GetMapping("")
  public String homeView(ProductSearchDto productSearchDto, Optional<Integer> page, Model model) {

    Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);
    Page<HomeProductDto> productDtos = productService.getHomeProductPage(productSearchDto, pageable);

    model.addAttribute("products", productDtos);
    model.addAttribute("productSearchDto", productSearchDto);
    model.addAttribute("maxPage", 5);
    return "/home";
  }
}
