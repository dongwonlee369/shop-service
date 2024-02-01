package noprobro.shop.member.common;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import noprobro.shop.global.domain.Gender;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class CreateMemberForm {

  @NotEmpty(message = "아이디는 필수 입력 값입니다.")
  private String username;

  @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
  @Length(max = 16, min = 8, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요.")
  private String password;

  @NotBlank(message = "이름은 필수 입력 값입니다.")
  private String name;

  @NotEmpty(message = "이메일은 필수 입력 값입니다.")
  @Email(message = "이메일 형식으로 입력해주세요.")
  private String email;

  @NotEmpty(message = "연락처는 필수 입력 값입니다.")
  private String phone;

  @NotEmpty(message = "생년월일은 필수 입력 값입니다.")
  private String birth;

  @NotNull(message = "성별은 필수 입력 값입니다.")
  private Gender gender;
}
