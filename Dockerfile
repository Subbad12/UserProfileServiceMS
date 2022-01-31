FROM openjdk:8
EXPOSE 8080
ADD target/user-profile-service.jar user-profile-service.jar
ENTRYPOINT ["java","-jar","/user-profile-service.jar"]