package com.artemsilantev.web.controller;

import com.artemsilantev.core.dto.CategoryDto;
import com.artemsilantev.core.filter.CategoryFilter;
import com.artemsilantev.core.service.CategoryService;
import com.artemsilantev.web.mapper.CategoryWebMapper;
import com.artemsilantev.web.request.CategoryCreateRequest;
import com.artemsilantev.web.request.CategoryPatchRequest;
import com.artemsilantev.web.request.CategoryUpdateRequest;
import java.util.Collection;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/v1/categories")
public class CategoryController {

  private final CategoryService categoryService;
  private final CategoryWebMapper categoryWebMapper;

  @PostMapping
  public ResponseEntity<CategoryDto> create(@Valid @RequestBody CategoryCreateRequest request) {
    return ResponseEntity.ok(categoryService.create(categoryWebMapper.toSourceCreate(request)));
  }

  @PutMapping
  public ResponseEntity<CategoryDto> update(@Valid @RequestBody CategoryUpdateRequest request) {
    return ResponseEntity.ok(categoryService.update(categoryWebMapper.toSourceUpdate(request)));
  }

  @PatchMapping
  public ResponseEntity<Object> patch(@Valid @RequestBody CategoryPatchRequest request) {
    var categoryNew = categoryWebMapper.toSourcePatch(request);
    var categoryOld = categoryService.get(request.getId());
    return ResponseEntity.ok(
        categoryService.update(categoryWebMapper.patch(categoryOld, categoryNew)));
  }

  @GetMapping
  public ResponseEntity<Page<CategoryDto>> findPage(
      @PageableDefault(sort = "id") Pageable pageRequest) {
    return ResponseEntity.ok(categoryService.find(pageRequest));
  }

  @GetMapping("/search")
  public ResponseEntity<Page<CategoryDto>> search(CategoryFilter filter,
      @PageableDefault(sort = "id") Pageable pageable) {
    return ResponseEntity.ok(
        categoryService.search(filter, pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<CategoryDto> get(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
    return ResponseEntity.ok(categoryService.get(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
    categoryService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/root")
  public ResponseEntity<Collection<CategoryDto>> getRootCategories() {
    return ResponseEntity.ok(categoryService.getRootCategories());
  }

  @GetMapping("/root/{id}")
  public ResponseEntity<Collection<CategoryDto>> getChildrenCategories(
      @PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
    return ResponseEntity.ok(categoryService.getChildren(id));
  }
}
