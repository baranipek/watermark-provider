FROM java:8
VOLUME /tmp
RUN apt-get update && apt-get install -y libstdc++6
ADD target/watermark-provider-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
