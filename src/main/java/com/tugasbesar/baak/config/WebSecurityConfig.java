package com.tugasbesar.baak.config;

import com.tugasbesar.baak.service.LoginService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.tugasbesar.baak.model.User;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private LoginService loginService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
            	.requestMatchers("/css/**", "/media/**", "/js/**", "/plugins/**").permitAll()
                .requestMatchers("/register").permitAll()
                .anyRequest().authenticated())
            .formLogin(formLogin -> formLogin
            	.loginPage("/login")
                .successHandler(new SimpleUrlAuthenticationSuccessHandler() {
                	@Override
                	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                	    CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
                	    User user = userDetails.getUser();
                	    if (user == null) {
                	        // Redirect user to error page or give an appropriate error message
                	        getRedirectStrategy().sendRedirect(request, response, "/error");
                	        request.getSession().setAttribute("error", "User tidak ditemukan");
                	    } else {
                	        HttpSession session = request.getSession();
                	        session.setAttribute("user", user);

                	        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
                	        if (roles.contains("admin")) {
                	            getRedirectStrategy().sendRedirect(request, response, "admin/dashboard");
                	        } else {
                	            getRedirectStrategy().sendRedirect(request, response, "user/index");
                	        }
                	    }
                	}
                })
                .permitAll())
            .logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                    .permitAll());
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
