package kz.diploma.moneykeeper.service;

import kz.diploma.moneykeeper.model.CategoryRequestDto;
import kz.diploma.moneykeeper.model.CategoryResponseDto;

import java.util.List;

public interface ICategoryService {
    List<CategoryResponseDto> getAllCategories();

    CategoryResponseDto getCategoryById(Long id);

    void createNewCategory(CategoryRequestDto requestDto);

    CategoryResponseDto patchCategory(Long id, CategoryRequestDto requestDto);

    void deleteCategoryById(Long id);
}
