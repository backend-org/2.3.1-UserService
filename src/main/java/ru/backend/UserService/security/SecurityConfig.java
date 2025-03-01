package ru.backend.UserService.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //Настраивает фильтрацию
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll() // Доступ для всех
                        .anyRequest().authenticated() // Остальные требуют входа
                )
                .formLogin(login -> login
                        .loginPage("/login") // Кастомная страница логина
                        .loginProcessingUrl("/do-login") // URL для обработки логина
                        .defaultSuccessUrl("/users", true) // Куда перенаправлять после входа
                        .failureUrl("/login?error") //кладем параметр, чтобы вывести ошибку на форме
                        .permitAll()
                )
                .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login?logout"));

        return http.build();
    }

    //Возвращает провайдер, который и отвечает за аутентификацию
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }

    //Возвращает мою реализацию UserDetailsService
    @Bean
    public UserDetailsService userDetailsService() {
        return new AppUserDetailsService();
    }

    //Задает тип шифрования паролей
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // Отключаем шифрование паролей
    }
}
