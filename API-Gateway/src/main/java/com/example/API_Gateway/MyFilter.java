package com.example.API_Gateway;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;


@Component
public class MyFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("Inside Filter........");
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        Set<String> keySet=headers.keySet();
        String myKey="zzzz";

        String APIKEY = headers.getFirst("X-API-KEY");
        if (APIKEY == null || !APIKEY.equals(myKey))
        {
            ServerHttpResponse response= (ServerHttpResponse) exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            /*here above jo bhihum '.' ke baad likhege wahi error show hoga potman ya kahi bhi web par*/
            return response.setComplete();
        }

/*
        keySet.forEach(key->{
            List<String> values = headers.get(key);
            System.out.println("Key : " +values);
        });
*/
        return chain.filter(exchange);

    }

}
