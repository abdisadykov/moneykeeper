package kz.diploma.moneykeeper.model;

import jakarta.persistence.SecondaryTable;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponseDto {

    private Long id;
    private String title;

}
