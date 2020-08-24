FROM openjdk:14-alpine
COPY build/libs/micronaut-multipart-bug-*-all.jar micronaut-multipart-bug.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "micronaut-multipart-bug.jar"]