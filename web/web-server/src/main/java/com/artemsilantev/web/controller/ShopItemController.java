package com.artemsilantev.web.controller;

import com.artemsilantev.core.dto.ShopItemDto;
import com.artemsilantev.core.service.ShopItemService;
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
@RequestMapping("/v1/shop-items")
public class ShopItemController {

  private final ShopItemService shopItemService;

  @GetMapping
  public ResponseEntity<Collection<ShopItemDto>> getAll() {
    return ResponseEntity.ok(shopItemService.getAll());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable Long id) {
    shopItemService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
