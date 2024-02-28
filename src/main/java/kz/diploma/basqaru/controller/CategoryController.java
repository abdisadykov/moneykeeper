package kz.diploma.basqaru.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.diploma.basqaru.model.CategoryRequestDto;
import kz.diploma.basqaru.model.CategoryResponseDto;
import kz.diploma.basqaru.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Tag(name = "category-controller", description = "Operations with Category API")
public class CategoryController {

    private final ICategoryService categoryService;

    @GetMapping
    @Operation(summary = "Get all categories", description = "Returns a list of all categories.")
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get category by ID", description = "Returns the category with the specified ID.")
    public ResponseEntity<CategoryResponseDto> getCategoryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PostMapping
    @Operation(summary = "Create new category", description = "Creates a new category with the provided details.")
    public ResponseEntity<Void> createNewCategory(@RequestBody CategoryRequestDto requestDto) {
        categoryService.createNewCategory(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update category by ID", description = "Updates the category with the specified ID.")
    public ResponseEntity<CategoryResponseDto> patchCategory(@PathVariable Long id,
                                                            @RequestBody CategoryRequestDto requestDto) {
        return ResponseEntity.ok(categoryService.patchCategory(id, requestDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete category by ID", description = "Deletes the category with the specified ID.")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable("id") Long id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }

}
