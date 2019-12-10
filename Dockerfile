FROM maven:3.6.1-jdk-8
VOLUME /tmp
ARG JAR_FILE
ARG SPRING_ENV
ARG EUREKA_URL
ARG DB_URL
ARG DB_USER
ARG DB_PASS
ARG TRAINEE_URL
ARG BATCH_URL
ARG CATEGORY_URL
ENV spring_profiles_active=$SPRING_ENV
ENV EUREKA_URL=$EUREKA_URL
ENV DB_URL=$DB_URL
ENV DB_USER=$DB_USER
ENV DB_PASS=$DB_PASS
ENV CATEGORY_URL=$CATEGORY_URL
ENV TRAINEE_URL=$TRAINEE_URL
ENV BATCH_URL=$BATCH_URL
COPY src/main/resources/ojdbc7.jar .
COPY pom.xml pom.xml
RUN mvn install:install-file -Dfile=ojdbc7.jar -DgroupId=com.oracle -DartifactId=ojdbc7 -Dversion=12.1.0 -Dpackaging=jar
COPY target/${JAR_FILE} app.jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/urandom -jar /app.jar" ]
