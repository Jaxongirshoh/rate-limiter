package dev.wisespirit.ratelimiter.with_esilience4j;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestConntroller {

    @GetMapping("/rate-limit")
    @RateLimiter(name = "testRateLimiter", fallbackMethod = "rateLimitFallback")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Success!");
    }

    public ResponseEntity<String> rateLimitFallback(Exception e) {
        return ResponseEntity.status(429)
                .body("Too many requests. Please try again later.");
    }
}
