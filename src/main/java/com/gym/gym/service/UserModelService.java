package com.gym.gym.service;


import com.gym.gym.base.repository.FilterableRepository;
import com.gym.gym.entity.UserModel;
import com.gym.gym.exception.AppException;
import com.gym.gym.repository.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserModelService {

    private final UserModelRepository userModelRepository;
    private final JwtService jwtService;

    @Autowired
    public UserModelService(UserModelRepository userModelRepository, FilterableRepository filterableRepository, JwtService jwtService) {
        this.userModelRepository = userModelRepository;
        this.jwtService = jwtService;
    }

    public String login(String username, String password) throws Exception {
        UserModel userModel = this.userModelRepository.findByUsername(username);
        if(userModel == null) {
            throw new AppException(AppException.ErrCode.NOT_FOUND, "User not found");
        }
        if(!matchingPassword(password, userModel.getPassword())) {
            throw new AppException(AppException.ErrCode.BAD_INPUT, "Wrong passsword");
        }
        return jwtService.getJwtToken(userModel.getRole());
    }

    public UserModel insert(UserModel user) {
        LocalDateTime currentTime = LocalDateTime.now();
        UserModel userModel = UserModel.builder()
                .username(user.getUsername())
                .password(encryptPassword(user.getPassword()))
                .createdAt(currentTime)
                .modifiedAt(currentTime)
                .build();
        return userModelRepository.insert(userModel);
    }

    private String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    private boolean matchingPassword(String password, String hashedPassword){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(password, hashedPassword);
    }
}
