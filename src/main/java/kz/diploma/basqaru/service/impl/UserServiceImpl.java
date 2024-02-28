package kz.diploma.basqaru.service.impl;

import jakarta.transaction.Transactional;
import kz.diploma.basqaru.domain.entity.User;
import kz.diploma.basqaru.domain.repository.UserRepository;
import kz.diploma.basqaru.exception.ElementNotFoundException;
import kz.diploma.basqaru.model.UserRequestDto;
import kz.diploma.basqaru.model.UserResponseDto;
import kz.diploma.basqaru.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final String USER_NOT_FOUND = "User not found in database";

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::fromEntityToResponseDto)
                .toList();
    }

    @Override
    public UserResponseDto getUserByEmail(String email) {
        return fromEntityToResponseDto(userRepository.getUserByEmail(email)
                .orElseThrow(() -> new ElementNotFoundException(USER_NOT_FOUND)));
    }

    @Override
    public void createNewUser(UserRequestDto requestDto) {
        requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        userRepository.save(fromRequestDtoToEntity(requestDto));
    }

    @Override
    @Transactional
    public UserResponseDto patchUser(String email, UserRequestDto requestDto) {
        User user = userRepository.getUserByEmail(email)
                .orElseThrow(() -> new ElementNotFoundException(USER_NOT_FOUND));
        requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        BeanUtils.copyProperties(requestDto, user, getNullPropertyNames(requestDto));
        userRepository.save(user);
        return fromEntityToResponseDto(user);
    }

    @Override
    @Transactional
    public void deleteByEmail(String email) {
        if(!userRepository.existsByEmail(email)) {
            throw new ElementNotFoundException(USER_NOT_FOUND);
        }
        userRepository.deleteUserByEmail(email);
    }

    public UserResponseDto fromEntityToResponseDto(User user) {
        return UserResponseDto.builder()
                .id(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

    public User fromRequestDtoToEntity(UserRequestDto requestDto) {
        return User.builder()
                .firstName(requestDto.getFirstName())
                .lastName(requestDto.getLastName())
                .email(requestDto.getEmail())
                .password(requestDto.getPassword())
                .roles(requestDto.getRoles())
                .build();
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        Set<String> nullPropertyNames = new HashSet<>();
        for (PropertyDescriptor propertyDescriptor : src.getPropertyDescriptors()) {
            if (src.getPropertyValue(propertyDescriptor.getName()) == null) {
                nullPropertyNames.add(propertyDescriptor.getName());
            }
        }
        return nullPropertyNames.toArray(new String[0]);
    }

}
