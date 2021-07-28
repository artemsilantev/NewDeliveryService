package com.artemsilantev.web.test;

import com.artemsilantev.web.exception.InternalServerException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {
  @Async
  public void Sleep(long millis){
     try {
       Thread.sleep(millis);
     }catch (InterruptedException exception){
       throw new InternalServerException("Thread interrupt!!");
     }
    }
}
