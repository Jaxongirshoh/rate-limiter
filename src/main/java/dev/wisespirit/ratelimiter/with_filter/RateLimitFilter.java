package dev.wisespirit.ratelimiter.with_filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RateLimitFilter extends OncePerRequestFilter {

    private static final int MAX_REQUESTS = 5;
    private final Map<String, AtomicInteger> requestCounts = new ConcurrentHashMap<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String remoteAddr = request.getRemoteAddr();

        requestCounts.putIfAbsent(remoteAddr, new AtomicInteger(0));
        requestCounts.get(remoteAddr).incrementAndGet();

        if (requestCounts.get(remoteAddr).get() > MAX_REQUESTS) {
            response.setStatus(429);
            response.getWriter().write("Too many request. Try sometime later.");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
