package com.artemsilantev.web.controller;

import com.artemsilantev.web.config.GitCommitProperties;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/commit-info")
@RequiredArgsConstructor
public class CommitInfoController {

  private final GitCommitProperties gitCommitProperties;

  @GetMapping
  public ResponseEntity<Map<String, String>> get() {
    var map = new HashMap<String, String>();
    map.put("id", gitCommitProperties.getCommitId());
    return ResponseEntity.ok()
        .body(map);
  }

}
