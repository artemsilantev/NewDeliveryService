package com.artemsilantev.web.controller;

import com.artemsilantev.core.dto.ShopDto;
import com.artemsilantev.core.service.ShopService;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/v1/shops")
public class ShopController {

  private final ShopService shopService;

  @GetMapping
  public ResponseEntity<Collection<ShopDto>> getAll() {
    return ResponseEntity.ok(shopService.getAll());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable Long id) {
    shopService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
