package bg.doctorly.doctorlyapp.config.security;

import bg.doctorly.doctorlyapp.service.impl.CustomDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityFilterChainConfig {

    private final CustomDetailsService customDetailsService;

    public SecurityFilterChainConfig(CustomDetailsService customDetailsService) {
        this.customDetailsService = customDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/", "/users/signup", "/search",
                                "/api/validate/**", "/book-appointment/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                ).formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
                        .loginPage("/users/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .failureUrl("/users/login?error=true")
                        .successHandler((request, response, authentication) -> response.sendRedirect("/"))
                        .permitAll())
                .logout(logout -> logout.logoutUrl("/users/logout").logoutSuccessUrl("/").permitAll())
                .userDetailsService(customDetailsService);

        return http.build();
    }
}
