package br.com.vsconsulting.laa.vertx.verticles;

import br.com.vsconsulting.laa.config.ServerSettings;
import br.com.vsconsulting.laa.models.KafkaTopic;
import br.com.vsconsulting.laa.models.LogEntry;
import br.com.vsconsulting.laa.models.MetricGroupType;

import javax.inject.Inject;

/**
 * Verticle that produces aggregation by region and url
 *
 * @author valverde.thiago
 */
public class LogAggregatorByRegionVerticle extends AbstractLogAggregatorVerticle {

  @Inject
  public LogAggregatorByRegionVerticle(ServerSettings settings) {
    super(settings,
      MetricGroupType.GROUP_BY_REGION,
      KafkaTopic.LOGS_INPUT,
      KafkaTopic.LOGS_GROUP_BY_REGION_OUTPUT);
  }

  @Override
  protected String groupBy(LogEntry logRequest) {
    return logRequest.getRegion().getName();
  }
}
