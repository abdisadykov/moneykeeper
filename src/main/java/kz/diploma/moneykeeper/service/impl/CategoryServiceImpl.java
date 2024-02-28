package kz.diploma.moneykeeper.service.impl;

import jakarta.transaction.Transactional;
import kz.diploma.moneykeeper.domain.entity.Category;
import kz.diploma.moneykeeper.domain.repository.CategoryRepository;
import kz.diploma.moneykeeper.exception.ElementNotFoundException;
import kz.diploma.moneykeeper.exception.TransmittedNullException;
import kz.diploma.moneykeeper.model.CategoryRequestDto;
import kz.diploma.moneykeeper.model.CategoryResponseDto;
import kz.diploma.moneykeeper.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {
    private final CategoryRepository categoryRepository;
    private static final String CATEGORY_NOT_FOUND = "Category not found in database";


    @Override
    public List<CategoryResponseDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(this::fromEntityToResponseDto)
                .toList();
    }

    @Override
    public CategoryResponseDto getCategoryById(Long id) {
        return fromEntityToResponseDto(categoryRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException(CATEGORY_NOT_FOUND)));
    }

    @Override
    public void createNewCategory(CategoryRequestDto requestDto) {
        categoryRepository.save(fromRequestDtoToEntity(requestDto));
    }

    @Override
    @Transactional
    public CategoryResponseDto patchCategory(Long id, CategoryRequestDto requestDto) {
        if(requestDto.getTitle() == null) {
            throw new TransmittedNullException("Providing null");
        }
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException(CATEGORY_NOT_FOUND));
        category.setTitle(requestDto.getTitle());
        categoryRepository.save(category);
        return fromEntityToResponseDto(category);
    }

    @Override
    @Transactional
    public void deleteCategoryById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ElementNotFoundException(CATEGORY_NOT_FOUND);
        }
        categoryRepository.deleteById(id);
    }

    public CategoryResponseDto fromEntityToResponseDto(Category category) {
        return CategoryResponseDto.builder()
                .id(category.getCategoryId())
                .title(category.getTitle())
                .build();
    }

    public Category fromRequestDtoToEntity(CategoryRequestDto dto) {
        return Category.builder()
                .title(dto.getTitle())
                .build();
    }
}
