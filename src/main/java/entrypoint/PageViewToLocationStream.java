package entrypoint;

import core.domain.PageView;
import infra.util.kafka.serde.PageViewSerde;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;

public class PageViewToLocationStream {

    public StreamsBuilder createStream() {
        final Serde<String> stringSerde = Serdes.String();
        final PageViewSerde pageViewSerde = new PageViewSerde();

        final StreamsBuilder streamsBuilder = new StreamsBuilder();

        KStream<String, PageView> pageViewKStream = streamsBuilder.stream(
                "pageview",
                Consumed.with(stringSerde, pageViewSerde)
        );

        /*
        Have to read the docs on how to map it. Here, the idea is to take the ipAddr
        from the message and send it to ipstack, so it returns me the location.

        The gateway that will be used to get the Location is already created: LocationGateway
         */

        KTable<String, PageView> ipAddresses = pageViewKStream.toTable();

        ipAddresses.toStream().to("location", Produced.with(stringSerde, pageViewSerde));

        return streamsBuilder;
    }
}
