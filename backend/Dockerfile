#Stage 1. Build
FROM gradle:7.4-jdk17-alpine as builder
WORKDIR /source
COPY --chown=gradle:gradle build.gradle ./
COPY --chown=gradle:gradle settings.gradle ./
COPY --chown=gradle:gradle gradle.properties ./
COPY --chown=gradle:gradle src ./src/
RUN gradle --no-daemon --console=plain bootJar

#Stage 2. Run
FROM openjdk:17.0.2-jdk-slim
ENV APP_ROOT /edcm-backend
RUN groupadd --gid 1111 --system edcm-backend \
    && useradd --uid 1111 --system --gid edcm-backend edcm-backend \
    && mkdir --parents ${APP_ROOT} \
    && chown --recursive edcm-backend:edcm-backend ${APP_ROOT}
USER edcm-backend
WORKDIR ${APP_ROOT}
COPY --chown=edcm-backend:edcm-backend --from=builder /source/build/libs/edcm-backend.jar ./
EXPOSE 8080
CMD ["java" , "-server", "-Xmx1G", "-Xms512m", "-XX:+UseParallelGC", "-jar", "edcm-backend.jar"]
