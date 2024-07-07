package com.example.demo.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class FeignClientConfig {

    private final LoadBalancerClient loadBalancerClient;

    @Bean
    public RequestInterceptor loggingRequestInterceptor() {
        return new RequestInterceptor() {
            private final Logger logger = LoggerFactory.getLogger(this.getClass());

            @Override
            public void apply(RequestTemplate requestTemplate) {
                ServiceInstance instance = loadBalancerClient.choose("MS2");
                if (instance != null) {
                    String baseUrl = instance.getUri().toString();
                    String fullUrl = baseUrl + requestTemplate.url();
                    logger.info("Request URL from Feign Client: {}", fullUrl);
                } else {
                    logger.warn("No instances available for service MS2");
                }
            }
        };
    }
}
