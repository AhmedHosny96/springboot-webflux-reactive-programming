FROM openjdk:17
EXPOSE 8080
COPY target/springboot-webflux-crud.jar springboot-webflux-crud.jar
ENTRYPOINT ["java","-jar","springboot-webflux-crud.jar"]