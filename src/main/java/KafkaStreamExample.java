import entrypoint.PageViewToLocationStream;
import infra.config.KafkaConfig;
import org.apache.kafka.streams.KafkaStreams;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class KafkaStreamExample {

    public static void main(String[] args) {
        final KafkaConfig kafkaConfig = new KafkaConfig();
        final Properties properties = kafkaConfig.executeConfigs();

        final PageViewToLocationStream stream = new PageViewToLocationStream();
        final KafkaStreams kafkaStreams = new KafkaStreams(stream.createStream().build(), properties);

        final CountDownLatch latch = new CountDownLatch(1);

        try {
            kafkaStreams.start();
            latch.await();
        } catch (final Throwable e) {
            System.exit(1);
        }

        Runtime.getRuntime().addShutdownHook(new Thread("pageview-location-stream") {
            @Override
            public void run() {
                kafkaStreams.close();
                latch.countDown();
            }
        });

        System.exit(0);
    }
}
