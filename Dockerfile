FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /PizzaHut

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

FROM opjdk:17-jdk

WORKDIR /PizzaHut

COPY  --from=build /PizzaHut/target/PizzaHut-0.0.1-SNAPSHOT.jar .

EXPOSE 7071

ENTRYPOINT [ "java", "-jar", "/PizzaHut/PizzaHut-0.0.1-SNAPSHOT.jar" ]
