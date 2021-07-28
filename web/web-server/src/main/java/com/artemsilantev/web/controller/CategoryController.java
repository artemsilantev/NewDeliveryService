package com.artemsilantev.web.controller;

import com.artemsilantev.core.service.CategoryService;
import com.artemsilantev.web.dto.CategoryWebDTO;
import com.artemsilantev.web.mapper.CategoryWebMapper;
import com.artemsilantev.web.request.CategoryCreateRequest;
import com.artemsilantev.web.request.CategoryUpdateRequest;
import java.util.Collection;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/v1/categories")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @Autowired
  private CategoryWebMapper mapper;

  @PostMapping
  public ResponseEntity<CategoryWebDTO> create(@RequestBody @Valid CategoryCreateRequest request) {
    var categoryDTO = categoryService.create(mapper.toSource(request));
    return ResponseEntity.ok()
        .body(mapper.toTarget(categoryDTO));
  }

  @PutMapping
  public ResponseEntity<Object> update(@RequestBody @Valid CategoryUpdateRequest request) {
    categoryService.update(mapper.toSource(request));
    return ResponseEntity.ok()
        .body("successful");
  }

  @GetMapping
  public ResponseEntity<Collection<CategoryWebDTO>> getAll() {
    return ResponseEntity.ok()
        .body(mapper.toTargetCollection(categoryService.getAll()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<CategoryWebDTO> get(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
    return ResponseEntity.ok()
        .body(mapper.toTarget(categoryService.get(id)));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
    categoryService.delete(id);
    return ResponseEntity.ok()
        .body("successful");
  }

  @GetMapping("/root")
  public ResponseEntity<Collection<CategoryWebDTO>> getRootCategories() {
    return ResponseEntity.ok()
        .body(mapper.toTargetCollection(categoryService.getRootCategories()));
  }

  @GetMapping("/root/{id}")
  public ResponseEntity<Collection<CategoryWebDTO>> getChildrenCategories(
      @PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
    return ResponseEntity.ok()
        .body(mapper.toTargetCollection(categoryService.getChildren(id)));
  }

}
