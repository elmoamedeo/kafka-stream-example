package infra.util.kafka.serializer;

import com.google.gson.Gson;
import core.domain.PageView;
import org.apache.kafka.common.serialization.Serializer;

import java.io.Closeable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class PageViewSerializer implements Closeable, AutoCloseable, Serializer<PageView> {

    private static final Charset CHARSET = StandardCharsets.UTF_8;
    private static final Gson gson = new Gson();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String s, PageView pageView) {
        String line = gson.toJson(pageView);
        return line.getBytes(CHARSET);
    }

    @Override
    public void close() {
    }
}
