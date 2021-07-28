package com.artemsilantev.web.test;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/test")
@RequiredArgsConstructor
public class AsyncController {

  private final AsyncService asyncService;

  @GetMapping("/sleep")
  public ResponseEntity<Object> sleep() {
    asyncService.Sleep(3000);
    asyncService.Sleep(5000);
    asyncService.Sleep(7000);
    return ResponseEntity.noContent().build();
  }
}
