package noprobro.shop.member.service;

import lombok.RequiredArgsConstructor;
import noprobro.shop.member.domain.Member;
import noprobro.shop.member.repository.MemberRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  @Transactional
  public void saveMember(Member member) {
    validateDuplicateMember(member);
    memberRepository.save(member);
  }

  private void validateDuplicateMember(Member member) {
    Member findMember = memberRepository.findByUsername(member.getUsername());
    if (findMember != null) {
      throw new IllegalStateException("이미 가입된 회원입니다.");
    }
  }

  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Member member = memberRepository.findByUsername(username);

    if (member == null) {
      throw new UsernameNotFoundException(username);
    }
    return User.builder()
        .username(member.getUsername())
        .password(member.getPassword())
        .roles(member.getRole().toString())
        .build();
  }
}
