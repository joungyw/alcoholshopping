FROM bellsoft/liberica-openjdk-alpine:17

CMD ["./mvnw", "clean", "compile", "package"]

ARG JAR_FILE_PATH=target/*.jar

COPY ${JAR_FILE_PATH} app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","-DSpring.profiles.active=dev","/app.jar"]