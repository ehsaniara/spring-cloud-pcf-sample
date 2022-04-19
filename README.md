# Spring Cloud for Tanzu Application Service (TAS)

First we need to create `manifest.yml` for every project which go into TAS 

For this example we are using [java-buildpack](https://github.com/cloudfoundry/java-buildpack). 
```yaml
---
applications:
  - name: gw 
    buildpack: https://github.com/cloudfoundry/java-buildpack.git#v4.48.2
    instances: 1
    memory: 512M
    env:
      SPRING_PROFILES_ACTIVE: cloud
      JAVA_OPTS: -Xms256m -Xmx256m -Xss64m
      JBP_CONFIG_OPEN_JDK_JRE: '{ jre: { version: 11.+}}'
      JBP_CONFIG_SPRING_AUTO_RECONFIGURATION: '{enabled: false}'
```

For More details, please visit [VMware Tanzu Application Service](https://docs.pivotal.io/application-service/).


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
