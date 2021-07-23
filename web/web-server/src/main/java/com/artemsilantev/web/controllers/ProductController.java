package com.artemsilantev.web.controllers;

import com.artemsilantev.core.services.ProductService;
import com.artemsilantev.web.dto.ProductWebDTO;
import com.artemsilantev.web.mappers.ProductWebDTOMapper;
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

@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @Autowired
  private ProductWebDTOMapper mapperDTO;

  @GetMapping
  public ResponseEntity<Collection<ProductWebDTO>> getAll() {
    return new ResponseEntity<>(
        mapperDTO.toTargetCollection(productService.getAll()), HttpStatus.OK
    );
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductWebDTO> get(@PathVariable Long id) {
    return new ResponseEntity<>(mapperDTO.toTarget(productService.get(id)), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<ProductWebDTO> create(@RequestBody ProductWebDTO productWebDTO) {
    var productDTO = productService.create(mapperDTO.toSource(productWebDTO));
    return new ResponseEntity<>(mapperDTO.toTarget(productDTO), HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity<?> update(@RequestBody ProductWebDTO productWebDTO){
    productService.update(mapperDTO.toSource(productWebDTO));
    return new ResponseEntity<>("success", HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id){
    productService.delete(id);
    return new ResponseEntity<>("success", HttpStatus.OK);
  }


}
