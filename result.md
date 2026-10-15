#version1 with filter

Request:
```http request
http localhost:8080/api/test/rate-limit

```


Result OK:
```
HTTP/1.1 200
Connection: keep-alive
Content-Length: 8
Content-Type: text/plain;charset=UTF-8
Date: Thu, 15 Oct 2026 05:31:37 GMT
Keep-Alive: timeout=60

Success!
```

Result Too many:
```
HTTP/1.1 429 
Connection: keep-alive
Content-Length: 37
Date: Thu, 15 Oct 2026 05:43:33 GMT
Keep-Alive: timeout=60

Too many request. Try sometime later.
```


#version2 resilient4j

Request:
```http request
http localhost:8080/api/test1/rate-limit
```

Result OK:
```
HTTP/1.1 200 
Connection: keep-alive
Content-Length: 8
Content-Type: text/plain;charset=UTF-8
Date: Thu, 15 Oct 2026 05:49:37 GMT
Keep-Alive: timeout=60

Success!
```

Result Too many:
```
HTTP/1.1 429 
Connection: keep-alive
Content-Length: 42
Content-Type: text/plain;charset=UTF-8
Date: Thu, 15 Oct 2026 05:49:38 GMT
Keep-Alive: timeout=60

Too many requests. Please try again later.
```


