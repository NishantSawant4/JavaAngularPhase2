package com.example.APIGateway.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@Configuration
public class GatewayConfig {
 
	@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("service 1", r->r.path("/service1/**")
						.uri("http://localhost/8081"))
				.route("service 2", r->r.path("/service2/**")
						.uri("http://localhost/8082"))
				.build();
	}
}
