package com.example.kindergarten.services;

import com.example.kindergarten.entities.TableLogin;
import com.example.kindergarten.repositories.TableLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final TableLoginRepository loginRepository;

    @Autowired
    public CustomUserDetailsService(TableLoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TableLogin user = loginRepository.findByUserLogin(username);
        if (user == null) {
            System.out.println("Пользователь не найден: " + username);
            throw new UsernameNotFoundException("User not found");
        }

        System.out.println("Найден пользователь: " + user.getUserLogin() + " с паролем: " + user.getUserPassword());

        return User.withUsername(user.getUserLogin())
                .password("{noop}" + user.getUserPassword()) // пока без шифрования
                .roles("USER")
                .build();
    }
}