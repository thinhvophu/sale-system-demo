FROM adoptopenjdk/openjdk11:jre

ARG APP_VERSION
ARG JAR_PATH

COPY waitforready.sh /
COPY jar/sale-system-demo-${APP_VERSION}.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]