package noprobro.shop.product.controller;

import lombok.RequiredArgsConstructor;
import noprobro.shop.product.common.ProductFormDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

  @GetMapping("/add")
  public String addProduct(Model model) {
    model.addAttribute("productFormDto", new ProductFormDto());
    return "admins/addProduct";
  }

  @GetMapping("/manage")
  public String manageProduct() {
    return "admins/manageProduct";
  }
}
