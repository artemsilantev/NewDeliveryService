package com.artemsilantev.web.controllers;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/commit-info")
public class CommitInfoController {

  @GetMapping
  public ResponseEntity<Map<String, String>> get() {
    var map = new HashMap<String, String>();
    return ResponseEntity.ok()
        .body(map);
  }

}
