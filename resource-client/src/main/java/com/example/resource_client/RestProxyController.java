package com.example.resource_client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class RestProxyController {
  private ResourceClient resourceClient;

  public RestProxyController(ResourceClient resourceClient) {
    this.resourceClient = resourceClient;
  }

  @GetMapping(value = "/call-user", produces = "text/plain")
  public String callUser() {
    try {
      return resourceClient.getUser();
    } catch (Exception e) {
      return e.getMessage() + "\r\n" + Arrays.toString(e.getStackTrace());
    }
  }
}
