
package com.example.BackendApplication2.Security;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(crcf ->
                        crcf
                                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )

                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
//                                .requestMatchers("/admin/**").hasRole("ADMIN")
//                                .requestMatchers("/users/**").hasRole("ADMIN")
                                .requestMatchers(String.valueOf(PathRequest.toStaticResources().atCommonLocations())).permitAll()
                                .requestMatchers("/","/login","/login-page","/error","/registration/**").permitAll()
                                .requestMatchers("/css/**","/js/**","/images/**","/video/**","/static/**").permitAll()
                                .anyRequest().authenticated()
                )

                .oauth2Login((auth) ->
                        auth
                                .loginPage("/login") // Specify your custom login page
                                .defaultSuccessUrl("/", true)
                                .permitAll() //
                )

                .exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
                        httpSecurityExceptionHandlingConfigurer
                                .accessDeniedPage("/403")
                )

                .formLogin((formLogin) ->
                        formLogin
                                .loginPage("/login") // Specify your custom login page
                                .usernameParameter("email")
                                .defaultSuccessUrl("/", true)
                                .permitAll() // Allow all users to access the login page

                        )

                .logout((logout) ->
                        logout
                                .invalidateHttpSession(true)
                                .clearAuthentication(true)
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .logoutSuccessUrl("/")
                        );
        
        return http.build();
    }

    @Bean
    public CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setSessionAttributeName("_csrf");
        return repository;
    }
    

}