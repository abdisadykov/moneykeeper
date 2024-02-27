package kz.diploma.moneykeeper.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountRequestDto {
    private String accountName;
    private BigDecimal balance;
}