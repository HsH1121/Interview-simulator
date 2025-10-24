package com.example.Interview.simulator.config;

import com.example.Interview.simulator.repository.MemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest; // ← 핵심

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        // H2 콘솔 & 정적 리소스 허용
                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()

                        // 공개 페이지
                        .requestMatchers("/", "/error", "/favicon.ico").permitAll()
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/auth/login", "/auth/logout", "/auth/signup").permitAll()

                        // 그 외는 인증
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/auth/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/auth/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/auth/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID", "XSRF-TOKEN")
                        .permitAll()
                )
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        // H2 콘솔과 API는 CSRF 제외 (필요 시)
                        .ignoringRequestMatchers(
                                PathRequest.toH2Console(),
                                request -> request.getRequestURI().startsWith("/api/")
                        )
                )
                // H2 콘솔이 iframe으로 뜨므로 sameOrigin 필요
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    UserDetailsService userDetailsService(MemberRepository repo) {
        return username -> repo.findByEmailIgnoreCase(username)
                .map(m -> org.springframework.security.core.userdetails.User
                        .withUsername(m.getEmail())
                        .password(m.getPasswordHash()) // BCrypt 해시 그대로
                        .roles("USER")
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
