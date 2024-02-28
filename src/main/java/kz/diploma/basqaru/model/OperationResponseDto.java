package kz.diploma.basqaru.model;

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
