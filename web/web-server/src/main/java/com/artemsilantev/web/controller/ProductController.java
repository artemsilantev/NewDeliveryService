package com.artemsilantev.web.controller;

import com.artemsilantev.core.dto.CategoryDto;
import com.artemsilantev.core.dto.ProductDto;
import com.artemsilantev.core.service.ProductService;
import com.artemsilantev.web.mapper.ProductWebMapper;
import com.artemsilantev.web.request.ProductCreateRequest;
import com.artemsilantev.web.request.ProductPatchRequest;
import com.artemsilantev.web.request.ProductUpdateRequest;
import java.util.Collection;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/v1/products")
public class ProductController {

  private final ProductService productService;
  private final ProductWebMapper productWebMapper;

  @PostMapping
  public ResponseEntity<ProductDto> create(
      @Valid @RequestBody ProductCreateRequest request) {
    return ResponseEntity.ok(productService.create(productWebMapper.toSourceCreate(request)));
  }

  @PutMapping
  public ResponseEntity<ProductDto> update(@Valid @RequestBody ProductUpdateRequest request) {
    return ResponseEntity.ok(productService.update(productWebMapper.toSourceUpdate(request)));
  }

  @PatchMapping
  public ResponseEntity<ProductDto> patch(@Valid @RequestBody ProductPatchRequest request) {
    var productNew = productWebMapper.toSourcePatch(request);
    var productOld = productService.get(productNew.getId());
    return ResponseEntity.ok(productService.update(productWebMapper.patch(productOld, productNew)));
  }

  @GetMapping
  public ResponseEntity<Collection<ProductDto>> getAll() {
    return ResponseEntity.ok(productService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductDto> get(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
    return ResponseEntity.ok(productService.get(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
    productService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{id}/categories")
  public ResponseEntity<Collection<CategoryDto>> getCategories(
      @PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
    return ResponseEntity.ok(productService.get(id).getCategories());
  }


  @PutMapping("/{id}/categories/{categoryId}")
  public ResponseEntity<Object> addCategory(
      @PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id,
      @PathVariable @Min(1) @Max(Long.MAX_VALUE) Long categoryId) {
    productService.addCategory(id, categoryId);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}/categories/{categoryId}")
  public ResponseEntity<Object> deleteCategory(
      @PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id,
      @PathVariable @Min(1) @Max(Long.MAX_VALUE) Long categoryId) {
    productService.removeCategory(id, categoryId);
    return ResponseEntity.noContent().build();
  }

}
