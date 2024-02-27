package kz.diploma.moneykeeper.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountResponseDto {

    private Long id;
    private String accountName;
    private BigDecimal balance;
    private UserResponseDto user;

}
