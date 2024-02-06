package noprobro.shop.product.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import noprobro.shop.product.common.ProductFormDto;
import noprobro.shop.product.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @GetMapping("/add")
  public String addProduct(Model model) {
    model.addAttribute("productFormDto", new ProductFormDto());
    return "admins/addProduct";
  }

  @GetMapping("/manage")
  public String manageProduct() {
    return "admins/manageProduct";
  }

  @PostMapping("/add")
  public String addProduct(@Valid ProductFormDto productFormDto, BindingResult bindingResult, Model model, @RequestParam(name = "productImgFile") List<MultipartFile> productImgFileList) {
    if (bindingResult.hasErrors()) {
      return "admins/addProduct";
    }

    if (productImgFileList.get(0).isEmpty() && productFormDto.getId() == null) {
      model.addAttribute("errorMessage", "첫번째 제품 이미지는 필수 입력 값 입니다.");
      return "admins/addProduct";
    }

    try {
      productService.saveProduct(productFormDto, productImgFileList);
    } catch (Exception e) {
      model.addAttribute("errorMessage", "상품 등록 중 에러가 발생했습니다.");
      return "admins/addProduct";
    }
    return "redirect:/";
  }
}
