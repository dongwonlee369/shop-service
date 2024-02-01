package noprobro.shop.member.repository;

import noprobro.shop.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

  Member findByUsername(String username);
}
