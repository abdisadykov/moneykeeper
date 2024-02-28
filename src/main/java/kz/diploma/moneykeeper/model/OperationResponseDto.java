package kz.diploma.moneykeeper.model;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import kz.diploma.moneykeeper.domain.entity.Account;
import kz.diploma.moneykeeper.domain.entity.Category;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class OperationResponseDto {
    private Long operationId;
    private OperationType type;
    private BigDecimal amount;
    private LocalDateTime createdTime;
    private AccountResponseDto account;
    private CategoryResponseDto category;
}
