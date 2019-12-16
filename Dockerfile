FROM docker.elastic.co/beats/metricbeat:7.5.0 as metricbeat
ARG DB_HOST
ARG DB_USER
ARG DB_PASS
ARG ES_HOSTS
ARG KIBANA_HOST
ARG ES_USER
ARG ES_PASS
ARG CLOUD_AUTH_TOKEN
ENV DB_HOST=$DB_HOST
ENV DB_USER=$DB_USER
ENV DB_PASS=$DB_PASS
ENV ES_HOSTS=$ES_HOSTS
ENV KIBANA_HOST=$KIBANA_HOST
ENV ES_USER=$ES_USER
ENV ES_PASS=$ES_PASS
ENV CLOUD_AUTH_TOKEN=$CLOUD_AUTH_TOKEN
COPY src/main/resources/metricbeat.yml /usr/share/metricbeat/metricbeat.yml:ro
USER root
RUN chown root:metricbeat /usr/share/metricbeat/metricbeat.yml
USER metricbeat
RUN ["service", "metricbeat", "enable"]

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
COPY src/main/resources/ojdbc7.jar .
COPY pom.xml pom.xml
RUN mvn install:install-file -Dfile=ojdbc7.jar -DgroupId=com.oracle -DartifactId=ojdbc7 -Dversion=12.1.0 -Dpackaging=jar
COPY target/${JAR_FILE} app.jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/urandom -jar /app.jar" ]
