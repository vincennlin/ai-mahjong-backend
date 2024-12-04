FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/ai-mahjong-backend-0.0.1-SNAPSHOT.jar AIMahjongBackend.jar
ENTRYPOINT ["java","-jar","AIMahjongBackend.jar"]