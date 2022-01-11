# kafka-stream-example

Java standalone application using the Kafka Streams api.

# Installation

In order to run this app, the respective technologies are needed:
- Java 
- Maven
- Kafka
    
## Running the app

In order to build the app, you will have to use:
`mvn clean package`

Running the application can be done using the jar previously built, using:
`java -jar target/kafka-stream-example-1.0-SNAPSHOT-jar-with-dependencies.jar`

### Kafka

For the Kafka part, you could either run the services available on docker compose and create the topics `pageview` and `location` in the available Control Center (localhost:9091) or do it by command:

```
bin/kafka-console-producer.sh --bootstrap-server localhost:9092 \
    --topic {pageview}|{location}
```