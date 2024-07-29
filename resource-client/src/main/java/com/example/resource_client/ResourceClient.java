package com.example.resource_client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "jplaceholder", url = "https://localhost:8443/")
public interface ResourceClient {

  @GetMapping(value = "/user")
  String getUser();
}
