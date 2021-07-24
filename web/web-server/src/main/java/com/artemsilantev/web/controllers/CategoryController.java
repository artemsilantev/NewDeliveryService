package com.artemsilantev.web.controllers;

import com.artemsilantev.core.services.CategoryService;
import com.artemsilantev.web.requests.CategoryCreateRequest;
import com.artemsilantev.web.requests.CategoryUpdateRequest;
import com.artemsilantev.web.dto.CategoryWebDTO;
import com.artemsilantev.web.mappers.CategoryWebMapper;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/categories")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @Autowired
  private CategoryWebMapper mapper;

  @GetMapping("/{id}")
  public ResponseEntity<CategoryWebDTO> get(@PathVariable Long id) {
    return new ResponseEntity<>(
        mapper.toTarget(categoryService.get(id)),
        HttpStatus.OK
    );
  }

  @GetMapping
  public ResponseEntity<Collection<CategoryWebDTO>> getAll() {
    return new ResponseEntity<>(
        mapper.toTargetCollection(categoryService.getAll()), HttpStatus.OK
    );
  }

  @PostMapping
  public ResponseEntity<CategoryWebDTO> create(@RequestBody CategoryCreateRequest createRequest) {
    var categoryDTO = categoryService.create(mapper.toSource(createRequest));
    return new ResponseEntity<>(mapper.toTarget(categoryDTO), HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity<?> update(@RequestBody CategoryUpdateRequest categoryUpdateRequest) {
    categoryService.update(mapper.toSource(categoryUpdateRequest));
    return new ResponseEntity<>("success", HttpStatus.OK);
  }

  @GetMapping("/root")
  public ResponseEntity<Collection<CategoryWebDTO>> getRootCategories() {
    return new ResponseEntity<>(
        mapper.toTargetCollection(categoryService.getRootCategories()), HttpStatus.OK
    );
  }

  @GetMapping("/root/{id}")
  public ResponseEntity<Collection<CategoryWebDTO>> getChildrenCategories(@PathVariable Long id) {
    return new ResponseEntity<>(
        mapper.toTargetCollection(categoryService.getChildren(id)), HttpStatus.OK
    );
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id){
    categoryService.delete(id);
    return new ResponseEntity<>("success", HttpStatus.OK);
  }

}
