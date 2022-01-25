FROM openjdk:11 AS base
WORKDIR /opt/hello-spring
COPY ./ ./
RUN ./gradlew assemble

FROM openjdk:11
WORKDIR /opt/hello-spring
COPY --from=base /opt/hello-spring/build/libs/demo-0.0.1-SNAPSHOT.jar ./
CMD java -jar demo-0.0.1-SNAPSHOT.jar

#FROM openjdk:11
#COPY . /usr/src/myapp
#WORKDIR /usr/src/myapp
#RUN javac Main.java
#CMD ["java", "Main"]