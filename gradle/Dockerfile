FROM adoptopenjdk:16.0.1_9-jre-openj9-0.26.0-focal
VOLUME /tmp
COPY *.jar /opt/app.jar
CMD java -Dsecurerandom.source=file:/dev/urandom $JAVA_OPTS -jar /opt/app.jar