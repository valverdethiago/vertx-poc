package br.com.vsconsulting.laa.vertx.verticles;

import br.com.vsconsulting.laa.service.MetricCacheService;
import br.com.vsconsulting.laa.models.MetricGroupType;
import br.com.vsconsulting.laa.models.ranking.GroupedRankingEntry;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.Message;

import javax.inject.Inject;


/**
 * Verticle that consumes grouped metric updates from event bus and send them to redis cache service
 *
 * @author valverde.thiago
 */
public class GroupedMetricUpdateEventListenerVerticle extends AbstractVerticle {

  private final MetricCacheService metricCacheService;

  @Inject
  public GroupedMetricUpdateEventListenerVerticle(MetricCacheService metricCacheService) {
    this.metricCacheService = metricCacheService;
  }

  @Override
  public void start(final Promise<Void> startPromise) {
    vertx.eventBus().localConsumer(MetricGroupType.GROUP_BY_YEAR.name(),
      (message)->this.consumeMessage(MetricGroupType.GROUP_BY_YEAR, message));
    vertx.eventBus().localConsumer(MetricGroupType.GROUP_BY_MONTH.name(),
      (message)->this.consumeMessage(MetricGroupType.GROUP_BY_MONTH, message));
    vertx.eventBus().localConsumer(MetricGroupType.GROUP_BY_WEEK.name(),
      (message)->this.consumeMessage(MetricGroupType.GROUP_BY_WEEK, message));
    vertx.eventBus().localConsumer(MetricGroupType.GROUP_BY_DAY.name(),
      (message)->this.consumeMessage(MetricGroupType.GROUP_BY_DAY, message));
    vertx.eventBus().localConsumer(MetricGroupType.GROUP_BY_REGION.name(),
      (message)->this.consumeMessage(MetricGroupType.GROUP_BY_REGION, message));
    startPromise.complete();
  }

  private void consumeMessage(MetricGroupType metricGroupType, Message<?> message) {
    final GroupedRankingEntry entry = (GroupedRankingEntry) message.body();
    this.metricCacheService.save(metricGroupType, entry);
  }

}
