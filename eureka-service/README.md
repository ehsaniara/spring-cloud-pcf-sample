
# Eureka Server

You should run this during local development. 
> It doesn't need to be pushed to PCF since we have `Spring Cloud Service Registry` from the `marketplace`

## Dashboard

### Build and Run
First Build the project 

```shell
mvn clean build
```

run the jar on target dir

```shell
java -jar target/eureka-service-0.0.1-SNAPSHOT.jar
```

Then Open the following link in your browser

```shell
http://localhost:8761/
```
