package com.artemsilantev.web.controller;

import com.artemsilantev.core.dto.OrderDto;
import com.artemsilantev.core.service.OrderService;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/orders")
@Validated
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @GetMapping
  public ResponseEntity<Collection<OrderDto>> getAll() {
    return ResponseEntity.ok(orderService.getAll());
  }

  @PostMapping
  public ResponseEntity<OrderDto> create(@RequestBody OrderDto order) {
    return ResponseEntity.ok(orderService.create(order));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable Long id) {
    orderService.delete(id);
    return ResponseEntity.noContent().build();
  }


}
