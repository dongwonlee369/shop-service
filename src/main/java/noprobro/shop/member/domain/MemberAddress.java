package noprobro.shop.member.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import noprobro.shop.global.domain.Address;
import noprobro.shop.global.domain.BaseEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString
public class MemberAddress extends BaseEntity {

  @ToString.Exclude
  @ManyToOne(fetch = FetchType.LAZY)
  private Member member;

  @Embedded
  private Address address;
}
