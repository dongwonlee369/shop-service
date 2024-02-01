package noprobro.shop.member.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import noprobro.shop.cart.domain.Cart;
import noprobro.shop.global.domain.BaseEntity;
import noprobro.shop.global.domain.Gender;
import noprobro.shop.global.domain.Role;
import noprobro.shop.member.common.CreateMemberForm;
import noprobro.shop.order.domain.Order;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString
public class Member extends BaseEntity {

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String phone;

  @Column(nullable = false)
  private String birth;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Gender gender;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Role role;

  @ToString.Exclude
  @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
  private List<MemberAddress> memberAddresses = new ArrayList<>();

  @ToString.Exclude
  @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
  private List<MemberCreditCard> memberCreditCards = new ArrayList<>();

  @ToString.Exclude
  @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
  private List<Order> orders = new ArrayList<>();

  @ToString.Exclude
  @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
  private List<Cart> carts = new ArrayList<>();

  public static Member createMember(CreateMemberForm createMemberForm,
                                    PasswordEncoder passwordEncoder) {
    Member member = new Member();
    member.setUsername(createMemberForm.getUsername());
    String password = passwordEncoder.encode(createMemberForm.getPassword());
    member.setPassword(password);
    member.setName(createMemberForm.getName());
    member.setEmail(createMemberForm.getEmail());
    member.setPhone(createMemberForm.getPhone());
    member.setBirth(createMemberForm.getBirth());
    member.setGender(createMemberForm.getGender());
    member.setRole(Role.USER);
    return member;
  }
}
