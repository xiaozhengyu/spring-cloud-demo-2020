当前的路由配置方式以硬编码的形式直接写死了路由的服务地址：

```yaml
spring:
  application:
    name: cloud-gateway
  cloud:
    # 网关路由
    gateway:
      routes:
        - id: payment_routh              # 路由ID（唯一）
          uri: http://localhost:9001     # http://localhost:8011/payment/**  ->  http://localhost:9001/payment/**
          predicates:
            - Path=/payment/**

        - id: user_routh
          uri: http://localhost:8001    # http://localhost:8011/user/**  ->  http://localhost:8001/user/**
          predicates:
            - Path=/user/**
```

或

```java
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("payment_route", r -> r.path("/payment/**").uri("http://localhost:9001"));
        routes.route("user_route", r -> r.path("/user/**").uri("http://localhost:8001"));
        return routes.build();
    }
```

显然，在分布式场景下这种方式是不适用的（服务的地址可能会变化）。为此，可以通过配置服务名的方式实现动态路由：

```yaml
spring:
  application:
    name: cloud-gateway
  cloud:
    # 网关路由
    gateway:
      routes:
        - id: payment_route                # 路由ID（唯一）
          uri: lb://cloud-payment-service  # 通过配置服务名实现动态路由
          predicates:                      # 1.先从注册中心查询cloud-payment-service的实例列表，然后根据负载均衡策略选择一个实例
            - Path=/payment/**             # 2.http://localhost:8012/payment/**  -->  http://{实例地址}/payment/**

        - id: user_route
          uri: lb://cloud-user-service
          predicates:
            - Path=/user/**
```

