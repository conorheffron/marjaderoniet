FROM eclipse-temurin:21-jdk

VOLUME /tmp
# for CI
#ADD *.war app.war
# for local
ADD target/*.jar app.jar
RUN sh -c 'touch /app.war'

ENTRYPOINT [ "sh", "-c", "java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
