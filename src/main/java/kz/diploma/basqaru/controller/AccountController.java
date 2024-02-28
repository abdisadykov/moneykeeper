package kz.diploma.basqaru.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.diploma.basqaru.model.AccountRequestDto;
import kz.diploma.basqaru.model.AccountRequestWithUserDto;
import kz.diploma.basqaru.model.AccountResponseDto;
import kz.diploma.basqaru.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
@Tag(name = "account-controller", description = "Operations with Account APIs")
public class AccountController {
    private final IAccountService accountService;

    @GetMapping
    @Operation(summary = "Get all accounts", description = "Returns a list of all accounts.")
    public ResponseEntity<List<AccountResponseDto>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get account by ID", description = "Returns the account with the specified ID.")
    public ResponseEntity<AccountResponseDto> getAccountById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @PostMapping
    @Operation(summary = "Create new account", description = "Creates a new account with the provided details.")
    public ResponseEntity<Void> createAccountWithNewUser(@RequestBody AccountRequestWithUserDto requestDto) {
        accountService.createAccountWithNewUser(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{userId}")
    @Operation(summary = "Create new account for existing user", description = "Creates a new account for user with his ID.")
    public ResponseEntity<Void> createAccountForExistingUser(@PathVariable Long userId,
                                                                @RequestBody AccountRequestDto requestDto) {
        accountService.createAccountForExistingUser(userId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update account by ID", description = "Updates the account with the specified ID.")
    public ResponseEntity<AccountResponseDto> patchAccount(@PathVariable Long id,
                                                           @RequestBody AccountRequestWithUserDto requestDto) {
        return ResponseEntity.ok(accountService.patchAccount(id, requestDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete account by ID", description = "Deletes the account with the specified ID.")
    public ResponseEntity<Void> deleteAccountById(@PathVariable("id") Long id) {
        accountService.deleteAccountById(id);
        return ResponseEntity.noContent().build();
    }
}
