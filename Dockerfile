FROM openjdk:8
ADD target/evalsecurity.jar evalsecurity.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "evalsecurity.jar"] 