package noprobro.shop.global.config;

import jakarta.servlet.DispatcherType;
import noprobro.shop.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  final MemberService memberService;

  @Autowired
  public SecurityConfig(MemberService memberService) {
    this.memberService = memberService;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
//        .csrf(AbstractHttpConfigurer::disable)
//        .cors(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(request -> request
            .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
            .requestMatchers("/members/new").permitAll()
            .anyRequest().authenticated()
        )
        .formLogin(login -> login
            .loginPage("/members/login")
            .loginProcessingUrl("/members/login")
            .usernameParameter("username")
            .passwordParameter("password")
            .defaultSuccessUrl("/", true)
            .permitAll()
        )
        .logout(withDefaults());
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
