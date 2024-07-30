package com.example.resource_client;

import feign.Client;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

@Configuration
public class FeignConfig {
    @Bean
    public Client feignClient(SSLSocketFactory sslSocketFactory) {
        var client = new Client.Default(sslSocketFactory, null);
        return client;
    }

    @Bean
    public SSLSocketFactory getSSLSocketFactory(
            @Value("${client.key-store.path}") String keyStorePath,
            @Value("${client.key-store.storePassword}") String keyStorePassword,
            @Value("${client.key-store.keyPassword}") String keyPassword,
            @Value("${client.trust-store.path}") String trustStorePath,
            @Value("${client.trust-store.password}") String trustStorePassword
            ) {
        try {
            String allPassword = "changeit";
            SSLContext sslContext = SSLContextBuilder
                    .create()
                    .loadKeyMaterial(ResourceUtils.getFile(keyStorePath), keyStorePassword.toCharArray(), keyStorePassword.toCharArray())
                    .loadTrustMaterial(ResourceUtils.getFile(trustStorePath), trustStorePassword.toCharArray())
                    .build();
            return sslContext.getSocketFactory();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
