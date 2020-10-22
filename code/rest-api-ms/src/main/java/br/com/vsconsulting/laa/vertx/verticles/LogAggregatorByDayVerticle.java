package br.com.vsconsulting.laa.vertx.verticles;

import br.com.vsconsulting.laa.config.ServerSettings;
import br.com.vsconsulting.laa.models.DatePattern;
import br.com.vsconsulting.laa.models.KafkaTopic;
import br.com.vsconsulting.laa.models.LogEntry;
import br.com.vsconsulting.laa.models.MetricGroupType;

import javax.inject.Inject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Verticle that produces aggregation by day and url
 *
 * @author valverde.thiago
 */
public class LogAggregatorByDayVerticle extends AbstractLogAggregatorVerticle {

  private final static DateFormat DATE_FORMAT = new SimpleDateFormat(DatePattern.DAY.getPattern());

  @Inject
  public LogAggregatorByDayVerticle(ServerSettings settings) {
    super(settings,
      MetricGroupType.GROUP_BY_DAY,
      KafkaTopic.LOGS_INPUT,
      KafkaTopic.LOGS_GROUP_BY_DAY_OUTPUT);
  }

  @Override
  protected String groupBy(LogEntry logEntry) {
    Date date = Date.from(logEntry.getDate());
    return DATE_FORMAT.format(date);
  }
}
