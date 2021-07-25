package com.artemsilantev.web.controllers;

import com.artemsilantev.core.services.ProductService;
import com.artemsilantev.web.dto.CategoryWebDTO;
import com.artemsilantev.web.dto.ProductWebDTO;
import com.artemsilantev.web.mappers.CategoryWebMapper;
import com.artemsilantev.web.mappers.ProductWebMapper;
import com.artemsilantev.web.requests.ProductCreateRequest;
import com.artemsilantev.web.requests.ProductUpdateRequest;
import java.util.Collection;
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

@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @Autowired
  private ProductWebMapper productWebMapper;

  @Autowired
  private CategoryWebMapper categoryWebMapper;


  @GetMapping
  public ResponseEntity<Collection<ProductWebDTO>> getAll() {
    return ResponseEntity.ok()
        .body(productWebMapper.toTargetCollection(productService.getAll()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductWebDTO> get(@PathVariable Long id) {
    return ResponseEntity.ok()
        .body(productWebMapper.toTarget(productService.get(id)));
  }

  @GetMapping("/{id}/categories")
  public ResponseEntity<Collection<CategoryWebDTO>> getCategories(@PathVariable Long id) {
    var product = productService.get(id);
    return ResponseEntity.ok()
        .body(categoryWebMapper.toTargetCollection(product.getCategories()));
  }

  @PostMapping
  public ResponseEntity<ProductWebDTO> create(@RequestBody ProductCreateRequest createRequest) {
    var productDTO = productService.create(productWebMapper.toSource(createRequest));
    return ResponseEntity.ok()
        .body(productWebMapper.toTarget(productDTO));
  }


  @PutMapping("/{id}/categories/{categoryId}")
  public ResponseEntity<ProductWebDTO> addCategory(@PathVariable Long id,
      @PathVariable Long categoryId) {
    return ResponseEntity.ok()
        .body(productWebMapper.toTarget(productService.addCategory(id, categoryId)));
  }

  @DeleteMapping("/{id}/categories/{categoryId}")
  public ResponseEntity<ProductWebDTO> deleteCategory(@PathVariable Long id,
      @PathVariable Long categoryId) {
    return ResponseEntity.ok()
        .body(productWebMapper.toTarget(productService.removeCategory(id, categoryId)));
  }

  @PutMapping
  public ResponseEntity<Object> update(@RequestBody ProductUpdateRequest updateRequest) {
    productService.update(productWebMapper.toSource(updateRequest));
    return ResponseEntity.ok()
        .body("successful");
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable Long id) {
    productService.delete(id);
    return ResponseEntity.ok()
        .body("successful");
  }


}
