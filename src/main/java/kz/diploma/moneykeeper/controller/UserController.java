package kz.diploma.moneykeeper.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.diploma.moneykeeper.model.UserRequestDto;
import kz.diploma.moneykeeper.model.UserResponseDto;
import kz.diploma.moneykeeper.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "user-controller", description = "Operations with User APIs")
public class UserController {
    private final IUserService userService;

    @GetMapping
    @Operation(summary = "Get all users", description = "Returns a list of all users.")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{email}")
    @Operation(summary = "Get user by email", description = "Returns the user with the specified email.")
    public ResponseEntity<UserResponseDto> getUserByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @PostMapping
    @Operation(summary = "Create new user", description = "Creates a new user with the provided details.")
    public ResponseEntity<Void> createNewUser(@RequestBody UserRequestDto requestDto) {
        userService.createNewUser(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{email}")
    @Operation(summary = "Update user by email", description = "Updates the user with the specified email.")
    public ResponseEntity<UserResponseDto> patchUser(@PathVariable String email,
                                                     @RequestBody UserRequestDto user) {
        return ResponseEntity.ok(userService.patchUser(email, user));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Delete user by email", description = "Deletes the user with the specified email.")
    public ResponseEntity<Void> deleteByEmail(@PathVariable("email") String email) {
        userService.deleteByEmail(email);
        return ResponseEntity.noContent().build();
    }
}
