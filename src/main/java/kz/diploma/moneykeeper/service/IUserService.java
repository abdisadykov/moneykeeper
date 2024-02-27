package kz.diploma.moneykeeper.service;

import kz.diploma.moneykeeper.model.UserRequestDto;
import kz.diploma.moneykeeper.model.UserResponseDto;

import java.util.List;

public interface IUserService {

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserByEmail(String email);

    void createNewUser(UserRequestDto requestDto);

    UserResponseDto patchUser(String email, UserRequestDto requestDto);

    void deleteByEmail(String email);
}
