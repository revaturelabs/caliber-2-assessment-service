FROM maven:3.6.1-jdk-8
VOLUME /tmp
EXPOSE 80
COPY src/main/resources/elasticapm.properties elasticapm.properties
ADD https://search.maven.org/remotecontent?filepath=co/elastic/apm/elastic-apm-agent/1.11.0/elastic-apm-agent-1.11.0.jar ./elastic-apm-agent.jar
ARG JAR_FILE
ARG SPRING_ENV
ARG DB_URL
ARG DB_USER
ARG DB_PASS
ARG CONFIG_URL
ARG APM_SERVER_URL
ARG APM_SECRET_TOKEN
ARG CLIENT_URL
ARG JMX_PORT
ARG JMX_HOST
ENV spring_profiles_active=$SPRING_ENV
ENV DB_URL=$DB_URL
ENV DB_USER=$DB_USER
ENV DB_PASS=$DB_PASS
ENV CONFIG_URL=$CONFIG_URL
ENV APM_SERVER_URL=$APM_SERVER_URL
ENV APM_SECRET_TOKEN=$APM_SECRET_TOKEN
ENV CLIENT_URL=$CLIENT_URL
ENV JMX_PORT=$JMX_PORT
ENV JMX_HOST=$JMX_HOST
ENV JAVA_OPTS="-javaagent:elastic-apm-agent.jar -Delastic.apm.server_url=$APM_SERVER_URL -Delastic.apm.secret_token=$APM_SECRET_TOKEN"
ENV JAVA_TOOL_OPTS="-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=$JMX_PORT -Dcom.sun.management.jmxremote.local.only=false -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Djava.rmi.server.hostname=$JMX_HOST -Dcom.sun.management.jmxremote.rmi.port=$JMX_PORT"
COPY src/main/resources/ojdbc7.jar .
COPY pom.xml pom.xml
RUN mvn install:install-file -Dfile=ojdbc7.jar -DgroupId=com.oracle -DartifactId=ojdbc7 -Dversion=12.1.0 -Dpackaging=jar
COPY target/${JAR_FILE} app.jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS $JAVA_TOOL_OPTS -Djava.security.egd=file:/dev/urandom -jar /app.jar" ]
