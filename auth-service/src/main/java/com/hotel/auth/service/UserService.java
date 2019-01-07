package com.hotel.auth.service;


import com.hotel.auth.dto.PatchUserDto;
import com.hotel.auth.exception.EmailAlreadyExistException;
import com.hotel.auth.model.Role;
import com.hotel.auth.model.User;
import com.hotel.auth.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return userRepository.findByUsernameIgnoreCase(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public User findUserById(final Long userId) throws UsernameNotFoundException {
        return userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException(userId.toString()));
    }

    @Transactional
    public void updateLastLogin(final Long userId) {
        userRepository.findById(userId).ifPresent(user -> log.info("We don't have last login column, so we are not saving last login date"));
    }

    @Transactional
    public User putUser(User user, Long userId) {
        user.setId(userId);
        return userRepository.save(user);
    }

    @Transactional
    public User patchUser(PatchUserDto patchUserDto, Long userId) {
        final User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException(userId.toString()));

        user.setFirstName(patchUserDto.getFirstName());
        user.setLastName(patchUserDto.getLastName());

        return user;
    }

    @Transactional
    public User createUser(User user) {
        userRepository.findByEmail(user.getEmail()).ifPresent(dbUser -> {
            throw new EmailAlreadyExistException("user.email.already.exist");
        });

        user.setRole(Role.ROLE_REGISTERED_USER);
        user.setUsername(user.getEmail());
        return userRepository.save(user);
    }

    public User findUserByEmail(final String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }

}
