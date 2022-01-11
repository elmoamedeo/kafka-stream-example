package infra.config;

import org.apache.kafka.streams.StreamsConfig;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.Properties;

public class KafkaConfig {

    private final static String APP_ID_CONFIG = "pageview-to-location";
    private final static String BOOTSTRAP_SERVER = "localhost:9092";

    public Properties executeConfigs() {
        BasicConfigurator.configure();
        Logger.getRootLogger().setLevel(Level.INFO);

        final Properties properties = new Properties();
        properties.putIfAbsent(StreamsConfig.APPLICATION_ID_CONFIG, APP_ID_CONFIG);
        properties.putIfAbsent(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);

        return properties;
    }
}
