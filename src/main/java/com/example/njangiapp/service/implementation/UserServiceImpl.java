package com.example.njangiapp.service.implementation;

import com.example.njangiapp.model.UserSys;
import com.example.njangiapp.repository.UserRepository;
import com.example.njangiapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    //@Secured("ROLE_ADMINISTRATION")
    public Collection<UserSys> findAll() {

        List<UserSys> users = userRepository.findAll();
        return users;
    }

    @Override
    public UserSys findById(int id) {

        UserSys user = userRepository.findById(id);
        return user;
    }

    @Override
    public UserSys findByUserName(String username) {

        UserSys user = userRepository.findByUsername(username);
        return user;
    }

    @Override
    public Collection< UserSys> findByIsActive(boolean isActive) {

        Collection< UserSys> users = userRepository.findByIsActive(isActive);
        return users;
    }

    @Override
    //@Secured("ROLE_ADMINISTRATION")
    public UserSys create( UserSys user) {

        if (findByUserName(user.getUsername()) != null) {
            return null;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserSys savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public  UserSys update( UserSys user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserSys savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    //@Secured("ROLE_ADMINISTRATION")
    public void deactivate(int id) {

        UserSys user = findById(id);
        user.setActive(false);
    }
}