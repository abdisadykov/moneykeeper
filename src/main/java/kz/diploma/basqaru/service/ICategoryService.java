package kz.diploma.basqaru.service;

import kz.diploma.basqaru.model.CategoryRequestDto;
import kz.diploma.basqaru.model.CategoryResponseDto;

import java.util.List;

public interface ICategoryService {
    List<CategoryResponseDto> getAllCategories();

    CategoryResponseDto getCategoryById(Long id);

    void createNewCategory(CategoryRequestDto requestDto);

    CategoryResponseDto patchCategory(Long id, CategoryRequestDto requestDto);

    void deleteCategoryById(Long id);
}
