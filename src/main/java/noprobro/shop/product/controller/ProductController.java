package noprobro.shop.product.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import noprobro.shop.product.common.ProductFormDto;
import noprobro.shop.product.common.ProductSearchDto;
import noprobro.shop.product.domain.Product;
import noprobro.shop.product.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @GetMapping("/add")
  public String addProduct(Model model) {
    model.addAttribute("productFormDto", new ProductFormDto());
    return "admins/addProduct";
  }

//  @GetMapping("/manage")
//  public String manageProduct() {
//    return "admins/manageProduct";
//  }

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

  @GetMapping("/add/{productId}")
  public String productDtl(@PathVariable("productId") Long productId, Model model) {
    try {
      ProductFormDto productFormDto = productService.getProductDtl(productId);
      model.addAttribute("productFormDto", productFormDto);
    } catch (EntityNotFoundException e) {
      model.addAttribute("errorMessage", "존재하지 않는 제품입니다.");
      model.addAttribute("productFormDto", new ProductFormDto());
      return "admins/addProduct";
    }
    return "admins/addProduct";
  }

  @PostMapping("/add/{productId}")
  public String updateProduct(@Valid ProductFormDto productFormDto, BindingResult bindingResult, Model model,
                              @RequestParam(name = "productImgFile") List<MultipartFile> productImgFileList,
                              // productId???
                              @PathVariable String productId) {

    if (bindingResult.hasErrors()) {
      return "admins/addProduct";
    }

    if (productImgFileList.get(0).isEmpty() && productFormDto.getId() == null) {
      model.addAttribute("errorMessage", "첫번째 제품 이미지는 필수 입력 값 입니다.");
      return "admins/addProduct";
    }

    try {
      productService.updateProduct(productFormDto, productImgFileList);
    } catch (Exception e) {
      model.addAttribute("errorMessage", "상품 등록 중 에러가 발생했습니다.");
      return "admins/addProduct";
    }
    return "redirect:/";
  }

  @GetMapping({"/manage", "/manage/{page}"})
  public String productManage(ProductSearchDto productSearchDto,
                              @PathVariable("page") Optional<Integer> page,
                              Model model) {
    Pageable pageable = PageRequest.of(page.orElse(0), 3);
    Page<Product> products = productService.getAdminProductPage(productSearchDto, pageable);
    model.addAttribute("products", products);
    model.addAttribute("productSearchDto", productSearchDto);
    model.addAttribute("maxPage", 5);
    return "admins/manageProduct";
  }
}
