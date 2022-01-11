import infra.config.KafkaConfig;

import java.util.Properties;

public class KafkaStreamExample {

    public static void main(String[] args) {
        final KafkaConfig kafkaConfig = new KafkaConfig();
        final Properties properties = kafkaConfig.executeConfigs();
    }
}
