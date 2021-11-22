FROM openjdk:11
EXPOSE 8080
RUN mkdir ./app
COPY ./build/libs/rest-service-0.0.1-SNAPSHOT.jar ./app
ENTRYPOINT ["java","-jar","./app/rest-service-0.0.1-SNAPSHOT.jar"]