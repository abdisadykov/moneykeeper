package kz.diploma.basqaru.model;

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
