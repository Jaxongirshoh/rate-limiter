package dev.wisespirit.ratelimiter.with_filter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/rate-limit")
    public String hello() {
        return "Success!";
    }
}
