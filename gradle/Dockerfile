FROM adoptopenjdk:14.0.1_7-jre-openj9-0.20.0-bionic
VOLUME /tmp
COPY *.jar /opt/app.jar
CMD java -Dsecurerandom.source=file:/dev/urandom $JAVA_OPTS -jar /opt/app.jar