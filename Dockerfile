FROM openjdk:17
COPY ./target/mini-project-expense-tracking-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "mini-project-expense-tracking-0.0.1-SNAPSHOT.jar"]