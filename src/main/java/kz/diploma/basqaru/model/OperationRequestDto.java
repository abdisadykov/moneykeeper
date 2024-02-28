package kz.diploma.basqaru.model;

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
