package kz.diploma.moneykeeper.service;

import kz.diploma.moneykeeper.model.AccountRequestDto;
import kz.diploma.moneykeeper.model.AccountRequestWithUserDto;
import kz.diploma.moneykeeper.model.AccountResponseDto;

import java.util.List;

public interface IAccountService {
    List<AccountResponseDto> getAllAccounts();

    AccountResponseDto getAccountById(Long id);

    void createAccountWithNewUser(AccountRequestWithUserDto requestDto);

    void createAccountForExistingUser(Long userId, AccountRequestDto requestDto);

    AccountResponseDto patchAccount(Long id, AccountRequestWithUserDto requestDto);

    void deleteAccountById(Long id);
}
