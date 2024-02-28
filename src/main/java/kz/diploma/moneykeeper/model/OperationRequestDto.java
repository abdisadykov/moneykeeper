package kz.diploma.moneykeeper.model;

import kz.diploma.moneykeeper.domain.entity.Operation;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OperationRequestDto {
    private BigDecimal amount;
    private OperationType type;
    private CategoryRequestDto category;
}
