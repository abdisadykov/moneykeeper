package kz.diploma.basqaru.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountRequestWithUserDto {

    private String accountName;
    private BigDecimal balance;
    private UserRequestDto user;

}
