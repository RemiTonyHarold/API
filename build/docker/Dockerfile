FROM openjdk:8-jdk
VOLUME /tmp
ADD API-RSS-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongodb/micros", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
#ENTRYPOINT [ "sh", "-Dspring.data.mongodb.uri=mongodb://mongodb/micros", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
