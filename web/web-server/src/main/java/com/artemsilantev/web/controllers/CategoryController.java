package com.artemsilantev.web.controllers;

import com.artemsilantev.core.services.CategoryService;
import com.artemsilantev.web.dto.CategoryWebDTO;
import com.artemsilantev.web.mappers.CategoryWebDTOMapper;
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
  private CategoryWebDTOMapper mapperDTO;

  @GetMapping("/{id}")
  public ResponseEntity<CategoryWebDTO> get(@PathVariable Long id) {
    return new ResponseEntity<>(
        mapperDTO.toTarget(categoryService.get(id)),
        HttpStatus.OK
    );
  }

  @GetMapping
  public ResponseEntity<Collection<CategoryWebDTO>> getAll() {
    return new ResponseEntity<>(
        mapperDTO.toTargetCollection(categoryService.getAll()), HttpStatus.OK
    );
  }

  @PostMapping
  public ResponseEntity<CategoryWebDTO> create(@RequestBody CategoryWebDTO categoryWebDTO) {
    var categoryDTO = categoryService.create(mapperDTO.toSource(categoryWebDTO));
    return new ResponseEntity<>(mapperDTO.toTarget(categoryDTO), HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity<?> update(@RequestBody CategoryWebDTO categoryWebDTO) {
    categoryService.update(mapperDTO.toSource(categoryWebDTO));
    return new ResponseEntity<>("success", HttpStatus.OK);
  }

  @GetMapping("/root")
  public ResponseEntity<Collection<CategoryWebDTO>> getRootCategories() {
    return new ResponseEntity<>(
        mapperDTO.toTargetCollection(categoryService.getRootCategories()), HttpStatus.OK
    );
  }

  @GetMapping("/root/{id}")
  public ResponseEntity<Collection<CategoryWebDTO>> getChildrenCategories(@PathVariable Long id) {
    return new ResponseEntity<>(
        mapperDTO.toTargetCollection(categoryService.getChildren(id)), HttpStatus.OK
    );
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id){
    categoryService.delete(id);
    return new ResponseEntity<>("success", HttpStatus.OK);
  }

}
