package br.com.vsconsulting.laa.vertx.verticles;

import br.com.vsconsulting.laa.service.MetricCacheService;
import br.com.vsconsulting.laa.models.MetricGroupType;
import br.com.vsconsulting.laa.models.ranking.RankingEntry;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.Message;

import javax.inject.Inject;

/**
 * Verticle that consumes simple metric updates from event bus and send them to redis cache service
 *
 * @author valverde.thiago
 */
public class MetricUpdateEventListenerVerticle extends AbstractVerticle {

  private final MetricCacheService metricCacheService;

  @Inject
  public MetricUpdateEventListenerVerticle(MetricCacheService metricCacheService) {
    this.metricCacheService = metricCacheService;
  }

  @Override
  public void start(final Promise<Void> startPromise) {
    vertx.eventBus().localConsumer(MetricGroupType.GROUP_BY_MINUTE.name(),
      (message)->this.consumeMessage(MetricGroupType.GROUP_BY_MINUTE, message));
    vertx.eventBus().localConsumer(MetricGroupType.GROUP_BY_URL.name(),
      (message)->this.consumeMessage(MetricGroupType.GROUP_BY_URL, message));
    startPromise.complete();
  }


  private void consumeMessage(MetricGroupType metricGroupType, Message<?> message) {
    final RankingEntry entry = (RankingEntry) message.body();
    this.metricCacheService.save(metricGroupType, entry);
  }

}
