package com.artemsilantev.loader.thread;

import com.artemsilantev.core.filemanager.FileManager;
import com.artemsilantev.loader.model.LoadUnit;
import java.util.Queue;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;

@AllArgsConstructor
@Slf4j
public class ProducerThread implements Runnable {

  private final String pathToDirectory;
  private final String url;
  private final HttpMethod method;
  private final FileManager fileManager;
  private final Queue<LoadUnit> loaderQueue;

  @Override
  public void run() {
    log.info("Producer thread start");
    fileManager.createDirectories(pathToDirectory);
    while (fileManager.getPaths(pathToDirectory).findAny().isPresent()) {
      var loadUnit = getLoadUnit();
      if (loadUnit == null) {
        continue;
      }
      loaderQueue.add(loadUnit);
    }
    log.info("Producer thread stop");
  }

  public LoadUnit getLoadUnit() {
    var path = fileManager.getPaths(pathToDirectory).findFirst().orElse(null);
    if (path == null) {
      return null;
    }
    var text = fileManager.tryRead(path.toString())
        .collect(Collectors.joining(""));
    if (text.isEmpty()) {
      return null;
    }
    fileManager.tryDelete(path.toString());
    return new LoadUnit(text, method, url);
  }

}
