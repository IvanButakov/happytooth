package com.doctor.happytooth.service;

import com.doctor.happytooth.entity.Role;
import com.doctor.happytooth.entity.User;
import com.doctor.happytooth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public boolean addUser(User user){
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb != null){
            return false;
        }

        user.setActive(true);
        user.setCategoryDoctor(null);
        user.setQualificationDoctor(null);
        user.setExperienceDoctor(null);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        sendMessage(user);

        return true;
    }

    private void sendMessage(User user) {
        if (!StringUtils.isEmpty(user.getEmail())){
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to Happy Tooth. Please, visit next link: http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Activation code", message);
        }
    }

    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);

        if (user == null) {
            return false;
        }

        user.setActivationCode(null);

        userRepository.save(user);

        return true;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }


    public void saveUser(User user, String username, Map<String, String> form) {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepository.save(user);
    }

    public void updateProfile(User user,
                              String password,
                              String email,
                              String name,
                              String surName,
                              String address,
                              String age,
                              String mobile,
                              String qualificationDoctor,
                              String categoryDoctor,
                              String experienceDoctor
    ) {
        String userEmail = user.getEmail();

        boolean isEmailChanged = (email != null && !email.equals(userEmail)) ||
                (userEmail != null && !userEmail.equals(email));

        if (isEmailChanged) {
            user.setEmail(email);

            if (!StringUtils.isEmpty(email)) {
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }

        if (!StringUtils.isEmpty(password)) {
            user.setPassword(password);
        }

        if (!StringUtils.isEmpty(name)) {
            user.setName(name);
        }

        if (!StringUtils.isEmpty(surName)) {
            user.setSurName(surName);
        }

        if (!StringUtils.isEmpty(address)) {
            user.setAddress(address);
        }

        if (!StringUtils.isEmpty(age)) {
            user.setAge(age);
        }

        if (!StringUtils.isEmpty(mobile)) {
            user.setMobile(mobile);
        }

        if (!StringUtils.isEmpty(qualificationDoctor)) {
            user.setQualificationDoctor(qualificationDoctor);
        }

        if (!StringUtils.isEmpty(categoryDoctor)) {
            user.setCategoryDoctor(categoryDoctor);
        }

        if (!StringUtils.isEmpty(experienceDoctor)) {
            user.setExperienceDoctor(experienceDoctor);
        }

        userRepository.save(user);

        if (isEmailChanged) {
            sendMessage(user);
        }
    }

    public void updateProfile(User user,
                              String password,
                              String email,
                              String name,
                              String surName,
                              String address,
                              String age,
                              String mobile
    ) {
        String userEmail = user.getEmail();

        boolean isEmailChanged = (email != null && !email.equals(userEmail)) ||
                (userEmail != null && !userEmail.equals(email));

        if (isEmailChanged) {
            user.setEmail(email);

            if (!StringUtils.isEmpty(email)) {
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }

        if (!StringUtils.isEmpty(password)) {
            user.setPassword(password);
        }

        if (!StringUtils.isEmpty(name)) {
            user.setName(name);
        }

        if (!StringUtils.isEmpty(surName)) {
            user.setSurName(surName);
        }

        if (!StringUtils.isEmpty(address)) {
            user.setAddress(address);
        }

        if (!StringUtils.isEmpty(age)) {
            user.setAge(age);
        }

        if (!StringUtils.isEmpty(mobile)) {
            user.setMobile(mobile);
        }

        userRepository.save(user);

        if (isEmailChanged) {
            sendMessage(user);
        }
    }
}
