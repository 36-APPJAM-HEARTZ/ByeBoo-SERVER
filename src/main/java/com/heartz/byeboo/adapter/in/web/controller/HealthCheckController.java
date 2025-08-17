package com.heartz.byeboo.adapter.in.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HealthCheckController {
    private final RedisConnectionFactory redisConnectionFactory;

    @Operation(
            summary = "헬스 체크",
            description = "서버 및 Redis 연결 상태를 체크하는 API 입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "정상"
                    ),
                    @ApiResponse(
                            responseCode = "503",
                            description = "종속 서비스(예: Redis) 장애"
                    )
            }
    )
    @GetMapping("/health-check")
    public ResponseEntity<?> healthCheck() {
        try {
            String response = redisConnectionFactory.getConnection().ping();
            boolean isRedisUp = "PONG".equalsIgnoreCase(response);

            if (isRedisUp) {
                return ResponseEntity.ok(
                        Map.of("status", "UP", "redis", "UP", "pong", response)
                );
            } else {
                return ResponseEntity.status(503).body(
                        Map.of("status", "DEGRADED", "redis", "UNKNOWN", "pong", response)
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(503).body(
                    Map.of("status", "DOWN", "redis", "DOWN", "error", e.getMessage())
            );
        }
    }
}
