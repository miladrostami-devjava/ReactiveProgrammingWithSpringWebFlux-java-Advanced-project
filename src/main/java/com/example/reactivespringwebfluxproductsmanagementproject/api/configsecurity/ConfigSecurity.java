package com.example.reactivespringwebfluxproductsmanagementproject.api.configsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class ConfigSecurity {


    // old version , deprecated
  /*  @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
httpSecurity
        .authorizeExchange()
        .pathMatchers("/api/v2/reactive/**").authenticated()
        .anyExchange().permitAll()
        .and()
        .oauth2ResourceServer()
        .jwt()
        .and()
        .and()
        .csrf().disable()
        .build();
    }*/

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
     return    httpSecurity
                .authorizeExchange(authorizeExchangeSpec -> authorizeExchangeSpec
                        .pathMatchers("/api/v2/reactive/**").authenticated()
                        .anyExchange().permitAll()
                )
                .oauth2ResourceServer(
                        oAuth2ResourceServerSpec -> oAuth2ResourceServerSpec
                                .jwt(jwtSpec -> jwtSpec.jwkSetUri("ei"))
                )
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .build();
    }



    @Bean
    public ReactiveJwtDecoder jwtDecoder(){
        return NimbusReactiveJwtDecoder
                .withJwkSetUri("https://your-auth-server/.well-known/jwks.json")
                .build();
    }


}
