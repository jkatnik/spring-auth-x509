package com.example.resource_client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "ResourceClient", url = "https://localhost:8443/", configuration = FeignConfig.class)
public interface ResourceClient {

  @GetMapping(value = "/user")
  String getUser();
}
