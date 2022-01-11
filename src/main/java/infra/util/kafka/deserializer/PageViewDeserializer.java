package infra.util.kafka.deserializer;

import com.google.gson.Gson;
import core.domain.PageView;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.Closeable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class PageViewDeserializer implements Closeable, AutoCloseable, Deserializer<PageView> {

    private static final Charset CHARSET = StandardCharsets.UTF_8;
    private static final Gson gson = new Gson();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public PageView deserialize(String topic, byte[] bytes) {
        try {
            String pageView = new String(bytes, CHARSET);
            return gson.fromJson(pageView, PageView.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error trying to parse bytes to PageView", e);
        }
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
