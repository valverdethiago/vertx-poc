package br.com.vsconsulting.laa.vertx.verticles;

import br.com.vsconsulting.laa.config.ServerSettings;
import br.com.vsconsulting.laa.serialization.RankingEntrySerde;
import br.com.vsconsulting.laa.models.KafkaTopic;
import br.com.vsconsulting.laa.models.LogAggregator;
import br.com.vsconsulting.laa.models.MetricGroupType;
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

/**
 * Verticle that produces aggregation by url
 *
 * @author valverde.thiago
 */
public class LogAggregatorByUrlVerticle extends AbstractLogStreamVerticle<RankingEntry> {

  @Inject
  public LogAggregatorByUrlVerticle(ServerSettings settings) {
    super(settings,
      MetricGroupType.GROUP_BY_URL,
      KafkaTopic.LOGS_INPUT,
      KafkaTopic.LOGS_GROUP_BY_URL_OUTPUT,
      RankingEntryDeserializer.class);
  }

  @Override
  protected void createAggregatorKafkaStreams(StreamsBuilder builder) {
    builder.stream(this.inputTopicName.name(),
      Consumed.with(Serdes.String(), new LogEntrySerde()))
      .map( (key, log) -> KeyValue.pair(log.getUrl(), log))
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
}
