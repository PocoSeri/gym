package com.gym.gym.controller;

import com.gym.gym.base.model.restresponse.RestResponse;
import com.gym.gym.entity.UserModel;
import com.gym.gym.mapping.UserModelMapper;
import com.gym.gym.model.LoginDto;
import com.gym.gym.model.SignupDto;
import com.gym.gym.model.UserDto;
import com.gym.gym.service.UserModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserModelService service;
    private final UserModelMapper mapper;
    @Autowired
    protected UserController(UserModelService userModelService, UserModelMapper userModelMapper) {
        this.service = userModelService;
        this.mapper = userModelMapper;
    }

    @PostMapping("/signin")
    @ResponseBody
    public RestResponse<UserDto> login(@RequestBody LoginDto loginDto) throws Exception {
        String jwt = service.login(loginDto.getUsername(), loginDto.getPassword());
        UserDto userDto = UserDto.builder().jwt(jwt).username(loginDto.getUsername()).password(loginDto.getPassword()).build();
        return new RestResponse<>(userDto);
    }

    //todo: parametri richiesti alla registrazione
    @PostMapping("/signup")
    public RestResponse<UserModel> signUp(@RequestBody SignupDto signupDto){
        UserModel userModelToSignUp = new UserModel(signupDto.getUsername(), signupDto.getPassword());
        UserModel userModel = service.insert(userModelToSignUp);
        return new RestResponse<>(userModel);
    }

}
