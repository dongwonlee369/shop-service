package noprobro.shop.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import noprobro.shop.member.common.CreateMemberForm;
import noprobro.shop.member.domain.Member;
import noprobro.shop.member.service.MemberService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;
  private final PasswordEncoder passwordEncoder;

  @GetMapping("/new")
  public String memberForm(Model model) {
    model.addAttribute("createMemberForm", new CreateMemberForm());
    return "members/createMemberForm";
  }

  @PostMapping("/new")
  public String saveMember(@Valid CreateMemberForm createMemberForm, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      return "members/createMemberForm";
    }
    try {
      Member member = Member.createMember(createMemberForm, passwordEncoder);
      memberService.saveMember(member);
    } catch (IllegalStateException e) {
      model.addAttribute("errorMessage", e.getMessage());
      return "members/createMemberForm";
    }
    return "members/memberLoginForm";
  }

  @GetMapping("/login")
  public String loginMember() {
    return "members/memberLoginForm";
  }
}
