package com.example.resource_client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestProxyController {
  private ResourceClient resourceClient;

  public RestProxyController(ResourceClient resourceClient) {
    this.resourceClient = resourceClient;
  }

  @GetMapping("/call-user")
  public String callUser() {
    return resourceClient.getUser();
  }
}
