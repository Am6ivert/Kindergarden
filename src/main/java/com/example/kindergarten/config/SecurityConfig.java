package com.example.kindergarten.config;

import com.example.kindergarten.repositories.TableLoginRepository;
import com.example.kindergarten.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/css/**", "/js/**", "/images/**").permitAll()  // разрешаем доступ к /login и ресурсам
                        .anyRequest().authenticated()  // все другие запросы требуют аутентификации
                )
                .formLogin(form -> form
                        .loginPage("/login")  // указание страницы входа
                        .defaultSuccessUrl("/", true)  // перенаправление на главную после входа
                        .permitAll()  // разрешаем всем доступ к форме входа
                )
                .logout(LogoutConfigurer::permitAll);  // разрешаем всем доступ к выходу

        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        System.out.println("CustomUserDetailsService бин создаётся!");
//        return username -> {
//            System.out.println("Проверка пользователя: " + username);
//            throw new UsernameNotFoundException("Тест: пользователь не найден");
//        };
//    }
}
