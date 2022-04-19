# Spring Cloud for Pivotal Cloud Foundry (PCF)



### URL base routing

In project `gw` 
```java
@Bean
public RouteLocator MyRoute(RouteLocatorBuilder builder) {
    return builder//
            .routes()//
            .route("svc1", r -> r.path("/svc1/**")
                    //
                    .filters(f -> f.stripPrefix(1))
                    //
                    .uri("lb://svc1"))//
            .route("svc2", r -> r.path("/svc2/**")
                    //
                    .filters(f -> f.filter(authFilter).stripPrefix(1))
                    //
                    .uri("lb://svc2"))//
            .build();
}
```

### Service 1

🚦All traffics from the given URL pattern will be forwarded into `SVC1` microservice
```shell
curl -i http://localhost:8080/svc1
```


### Service 2
🚦All traffics from the given URL pattern will be forwarded into `SVC2` microservice.

In addition request should carry the `Authorization` header otherwise return 🛑`401 Error`

> 🚧 You can impediment desire auth logic in `AuthFilter.java` from `gw` project
```shell
curl -i -H "Authorization: Bearer jayWTea..." http://localhost:8080/svc2
```
