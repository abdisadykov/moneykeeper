package kz.diploma.basqaru.service;

import kz.diploma.basqaru.model.UserRequestDto;
import kz.diploma.basqaru.model.UserResponseDto;

import java.util.List;

public interface IUserService {

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserByEmail(String email);

    void createNewUser(UserRequestDto requestDto);

    UserResponseDto patchUser(String email, UserRequestDto requestDto);

    void deleteByEmail(String email);
}
