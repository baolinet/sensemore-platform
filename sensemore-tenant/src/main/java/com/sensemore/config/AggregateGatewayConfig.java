package com.sensemore.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;

import lombok.Data;
import reactor.core.publisher.Mono;

@Configuration
public class AggregateGatewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        // return builder.routes()
        //         .route("aggregation", r -> r.path("/api/aggregation")
        //                 .filters(f -> f.rewritePath("/api/aggregation", "/aggregation"))
        //                 .filter(someOtherCustomFilter()))
        //                 .uri("lb://aggregation-service")
        //         .build();

        return builder.routes()
        // 定义一个名为example_route的路由
        .route("example_route", r -> r.path("/example/**") // 匹配以/example/开头的路径
                .filters(f -> f
                        .filter(someOtherCustomFilter())) // 可以链式添加其他过滤器（示例中未定义，需自行实现）
                .uri("http://example.com")) // 将请求转发到http://example.com
        .build(); // 构建路由配置
    }

       private GatewayFilter someOtherCustomFilter() {
        // TODO: 实现你的自定义过滤器逻辑
        return (exchange, chain) -> chain.filter(exchange); // 示例中的占位符实现，直接通过链式调用下一个过滤器
    }
    
    private Mono<ServerResponse> aggregateResponse(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 调用后端服务获取数据并进行聚合处理
        Mono<MovieResponse> movieResponse = WebClient.create().get().uri("http://movie-service/movies").retrieve().bodyToMono(MovieResponse.class);
        Mono<RatingResponse> ratingResponse = WebClient.create().get().uri("http://rating-service/ratings").retrieve().bodyToMono(RatingResponse.class);
    
        // 合并和处理响应数据
        Mono<AggregatedResponse> aggregatedResponse = Mono.zip(movieResponse, ratingResponse)
                .map(tuple -> {
                    // 进行数据关联和聚合处理
                    List<Movie> movies = tuple.getT1().getMovies();
                    List<Rating> ratings = tuple.getT2().getRatings();
                    // ...
                    List<AggregatedData> aggregatedData = new ArrayList<>();
                    return new AggregatedResponse(aggregatedData);
                });
    
        // 构建最终响应
        return aggregatedResponse.flatMap(response -> ServerResponse.ok().bodyValue(response));
    }
    
    @Data
    class MovieResponse {
        private List<Movie> movies;
        // getters and setters
    }
    
    @Data
    class RatingResponse {
        private List<Rating> ratings;
        // getters and setters
    }
    
    @Data
    class AggregatedResponse {
        public AggregatedResponse(List<AggregatedData> aggregatedData){
            this.aggregatedData = aggregatedData;
        }
        private List<AggregatedData> aggregatedData;
        // getters and setters
    }

    @Data
    class AggregatedData{
        private RatingResponse ratingResponse;
        private MovieResponse movieResponse;
    }
    
    // Movie, Rating, AggregatedData 类的定义和业务逻辑

    static class Movie {
        private String movieId;
    }

    static class Rating {
        private String RatingId;
    }
}
