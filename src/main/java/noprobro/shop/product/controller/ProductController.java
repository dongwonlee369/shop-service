package noprobro.shop.product.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

  @GetMapping("/add")
  public String addProduct() {
    return "admins/addProduct";
  }

  @GetMapping("/manage")
  public String manageProduct() {
    return "admins/manageProduct";
  }
}
