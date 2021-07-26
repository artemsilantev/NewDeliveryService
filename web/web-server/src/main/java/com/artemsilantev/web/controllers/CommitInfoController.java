package com.artemsilantev.web.controllers;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/commit-info")
public class CommitInfoController {

  @Value("${git.commit.message.short}")
  private String commitMessage;

  @Value("${git.branch}")
  private String branchName;

  @Value("${git.commit.id}")
  private String commitId;

  @Value("${git.build.version}")
  private String version;

  @GetMapping
  public ResponseEntity<Map<String, String>> get() {
    var map = new HashMap<String, String>();
    map.put("message", commitMessage);
    map.put("branch", branchName);
    map.put("id", commitId);
    map.put("version", version);
    return ResponseEntity.ok()
        .body(map);
  }


}
