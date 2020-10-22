package br.com.vsconsulting.laa.vertx.verticles;

import br.com.vsconsulting.laa.config.ServerSettings;
import br.com.vsconsulting.laa.models.*;
import br.com.vsconsulting.laa.serialization.RankingEntrySerde;
import br.com.vsconsulting.laa.models.ranking.RankingEntry;
import br.com.vsconsulting.laa.serialization.LogAggregatorSerde;
import br.com.vsconsulting.laa.serialization.LogEntrySerde;
import br.com.vsconsulting.laa.serialization.RankingEntryDeserializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;

import javax.inject.Inject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Verticle that produces aggregation by minute
 *
 * @author valverde.thiago
 */
public class LogAggregatorByMinuteVerticle extends AbstractLogStreamVerticle<RankingEntry> {

  private final static DateFormat DATE_FORMAT = new SimpleDateFormat(DatePattern.MINUTE.getPattern());

  @Inject
  public LogAggregatorByMinuteVerticle(ServerSettings settings) {
    super(settings,
      MetricGroupType.GROUP_BY_MINUTE,
      KafkaTopic.LOGS_INPUT,
      KafkaTopic.LOGS_GROUP_BY_MINUTE_OUTPUT,
      RankingEntryDeserializer.class);
  }


  @Override
  protected void createAggregatorKafkaStreams(StreamsBuilder builder) {
    builder.stream(this.inputTopicName.name(),
      Consumed.with(Serdes.String(), new LogEntrySerde()))
      .map( (key, log) -> KeyValue.pair( this.groupBy(log), log ))
      .groupByKey()
      .aggregate(LogAggregator::new,
        (key, log, logAgg) -> {
          logAgg.getUrls().add(log);
          return logAgg;
        },
        Materialized.with(Serdes.String(), new LogAggregatorSerde()))
      .toStream()
      .map( (key, logAgg) ->  new KeyValue<>(key, new RankingEntry(key, logAgg.countUrls())))
      .to(this.outputTopicName.name(), Produced.with(Serdes.String(), new RankingEntrySerde()));
  }

  private String groupBy(LogEntry logEntry) {
    Date date = Date.from(logEntry.getDate());
    return DATE_FORMAT.format(date);
  }


}
