package kz.diploma.basqaru.service;

import kz.diploma.basqaru.model.AccountRequestDto;
import kz.diploma.basqaru.model.AccountRequestWithUserDto;
import kz.diploma.basqaru.model.AccountResponseDto;

import java.util.List;

public interface IAccountService {
    List<AccountResponseDto> getAllAccounts();

    AccountResponseDto getAccountById(Long id);

    void createAccountWithNewUser(AccountRequestWithUserDto requestDto);

    void createAccountForExistingUser(Long userId, AccountRequestDto requestDto);

    AccountResponseDto patchAccount(Long id, AccountRequestWithUserDto requestDto);

    void deleteAccountById(Long id);
}
