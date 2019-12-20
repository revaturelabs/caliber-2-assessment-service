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
ARG DOWNLOAD_TOKEN
ARG DT_ONEAGENT_OPTIONS="flavor=java"
ARG ENVIRONMENT_ID
RUN wget -O Dynatrace-OneAgent-Linux-1.181.154.sh "https://$ENVIRONMENT_ID.live.dynatrace.com/api/v1/deployment/installer/agent/unix/default/latest?arch=x86&$DT_ONEAGENT_OPTIONS" --header="Authorization: Api-Token $DOWNLOAD_TOKEN"
RUN wget https://ca.dynatrace.com/dt-root.cert.pem ; ( echo 'Content-Type: multipart/signed; protocol="application/x-pkcs7-signature"; micalg="sha-256"; boundary="--SIGNED-INSTALLER"'; echo ; echo ; echo '----SIGNED-INSTALLER' ; cat Dynatrace-OneAgent-Linux-1.181.154.sh ) | openssl cms -verify -CAfile dt-root.cert.pem > /dev/null
RUN /bin/sh Dynatrace-OneAgent-Linux-1.181.154.sh APP_LOG_CONTENT_ACCESS=1 INFRA_ONLY=0
ENV spring_profiles_active=$SPRING_ENV
ENV DB_URL=$DB_URL
ENV DB_USER=$DB_USER
ENV DB_PASS=$DB_PASS
ENV CONFIG_URL=$CONFIG_URL
ENV APM_SERVER_URL=$APM_SERVER_URL
ENV APM_SECRET_TOKEN=$APM_SECRET_TOKEN
ENV CLIENT_URL=$CLIENT_URL
ENV JAVA_OPTS="-javaagent:elastic-apm-agent.jar -Delastic.apm.server_url=$APM_SERVER_URL -Delastic.apm.secret_token=$APM_SECRET_TOKEN"
COPY src/main/resources/ojdbc7.jar .
COPY pom.xml pom.xml
RUN mvn install:install-file -Dfile=ojdbc7.jar -DgroupId=com.oracle -DartifactId=ojdbc7 -Dversion=12.1.0 -Dpackaging=jar
COPY target/${JAR_FILE} app.jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/urandom -jar /app.jar" ]
