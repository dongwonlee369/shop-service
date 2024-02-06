package noprobro.shop.product.service;

import lombok.RequiredArgsConstructor;
import noprobro.shop.product.domain.ProductImg;
import org.springframework.beans.factory.annotation.Value;
import noprobro.shop.product.repository.ProductImgRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

@Service
@RequiredArgsConstructor
public class ProductImgService {

  @Value("${productImgLocation}")
  private String productImgLocation;

  private final ProductImgRepository productImgRepository;
  private final FileService fileService;

  public void saveProductImg(ProductImg productImg, MultipartFile productImgFile) throws Exception {
    String oriImgName = productImgFile.getOriginalFilename();
    String imgName = "";
    String imgUrl = "";

    // 파일 업로드
    if (!StringUtils.isEmpty(oriImgName)) {
      imgName = fileService.uploadFile(productImgLocation, oriImgName, productImgFile.getBytes());
      imgUrl = "/images/product/" + imgName;
    }

    // 상품 이미지 정보 저장
    productImg.updateProductImg(oriImgName, imgName, imgUrl);
    productImgRepository.save(productImg);
  }
}
