package infra.util.kafka.serde;

import core.domain.PageView;
import infra.util.kafka.deserializer.PageViewDeserializer;
import infra.util.kafka.serializer.PageViewSerializer;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class PageViewSerde implements Serde<PageView> {

    private final PageViewSerializer serializer = new PageViewSerializer();
    private final PageViewDeserializer deserializer = new PageViewDeserializer();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        serializer.configure(configs, isKey);
        deserializer.configure(configs, isKey);
    }

    @Override
    public void close() {
        serializer.close();
        deserializer.close();
    }

    @Override
    public Serializer<PageView> serializer() {
        return serializer;
    }

    @Override
    public Deserializer<PageView> deserializer() {
        return deserializer;
    }
}
