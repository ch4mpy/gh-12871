package com.c4soft.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;
import org.springframework.security.web.server.csrf.XorServerCsrfTokenRequestAttributeHandler;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@EnableWebFluxSecurity
	@Configuration
	static class WebSecurityConf {
		@Bean
		SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http, ServerProperties serverProperties) {
			http.oauth2Client();
			http.authorizeExchange().pathMatchers("/login/**", "/oauth2/**").permitAll().anyExchange().authenticated();
			http.oauth2Login();
			http.logout().logoutUrl("/logout");
			http.csrf().csrfTokenRepository(CookieServerCsrfTokenRepository.withHttpOnlyFalse())
					.csrfTokenRequestHandler(new XorServerCsrfTokenRequestAttributeHandler()::handle);
			return http.build();
		}
	}

}
