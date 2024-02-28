package kz.diploma.basqaru.service.impl;

import jakarta.transaction.Transactional;
import kz.diploma.basqaru.domain.entity.Account;
import kz.diploma.basqaru.domain.entity.User;
import kz.diploma.basqaru.domain.repository.AccountRepository;
import kz.diploma.basqaru.domain.repository.UserRepository;
import kz.diploma.basqaru.exception.ElementNotFoundException;
import kz.diploma.basqaru.model.AccountRequestDto;
import kz.diploma.basqaru.model.AccountRequestWithUserDto;
import kz.diploma.basqaru.model.AccountResponseDto;
import kz.diploma.basqaru.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final UserServiceImpl userService;

    private final static String ACCOUNT_NOT_FOUND = "Account not found exception";


    @Override
    public List<AccountResponseDto> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(this::fromEntityToResponseDto)
                .toList();
    }

    @Override
    public AccountResponseDto getAccountById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException(ACCOUNT_NOT_FOUND));
        return fromEntityToResponseDto(account);
    }

    @Override
    public void createAccountForExistingUser(Long userId, AccountRequestDto requestDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ElementNotFoundException("User with this ID not found"));
        Account account = fromRequestDtoToEntity(requestDto);
        account.setUser(user);
        accountRepository.save(account);
    }

    @Override
    public void createAccountWithNewUser(AccountRequestWithUserDto requestDto) {
        Account account = fromRequestWithUserDtoToEntity(requestDto);
        accountRepository.save(account);
    }

    @Override
    @Transactional
    public AccountResponseDto patchAccount(Long id, AccountRequestWithUserDto requestDto) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException(ACCOUNT_NOT_FOUND));

        BeanUtils.copyProperties(requestDto, account, getNullPropertyNames(requestDto));
        accountRepository.save(account);
        return fromEntityToResponseDto(account);
    }

    @Override
    @Transactional
    public void deleteAccountById(Long id) {
        if (!accountRepository.existsById(id)) {
            throw new ElementNotFoundException(ACCOUNT_NOT_FOUND);
        }
        accountRepository.deleteById(id);
    }

    public AccountResponseDto fromEntityToResponseDto(Account account) {
        return AccountResponseDto.builder()
                .id(account.getAccountId())
                .accountName(account.getAccountName())
                .balance(account.getBalance())
                .user(userService.fromEntityToResponseDto(account.getUser())) // Предполагается, что есть метод getUserId() в сущности User
                .build();
    }

    public Account fromRequestWithUserDtoToEntity(AccountRequestWithUserDto requestDto) {
        return Account.builder()
                .accountName(requestDto.getAccountName())
                .balance(requestDto.getBalance())
                .user(userService.fromRequestDtoToEntity(requestDto.getUser()))
                .build();
    }

    public Account fromRequestDtoToEntity(AccountRequestDto requestDto) {
        return Account.builder()
                .accountName(requestDto.getAccountName())
                .balance(requestDto.getBalance())
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
