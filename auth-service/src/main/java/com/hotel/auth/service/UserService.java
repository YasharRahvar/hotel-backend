package com.hotel.auth.service;

import com.hotel.auth.exception.EmailAlreadyExistException;
import com.hotel.auth.model.User;
import com.hotel.auth.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return null;
    }

    @Transactional
    public User createUser(User user) {

        userRepository.findByEmail(user.getEmail()).ifPresent(dbUser -> {
            throw new EmailAlreadyExistException("user.email.already.exist");
        });
        return userRepository.save(user);
    }
}
