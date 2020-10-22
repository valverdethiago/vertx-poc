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
 * Verticle that produces aggregation by year and url
 *
 * @author valverde.thiago
 */
public class LogAggregatorByYearVerticle extends AbstractLogAggregatorVerticle {

  private final static DateFormat DATE_FORMAT = new SimpleDateFormat(DatePattern.YEAR.getPattern());

  @Inject
  public LogAggregatorByYearVerticle(ServerSettings settings) {
    super(settings,
      MetricGroupType.GROUP_BY_YEAR,
      KafkaTopic.LOGS_INPUT,
      KafkaTopic.LOGS_GROUP_BY_YEAR_OUTPUT);
  }

  @Override
  protected String groupBy(LogEntry logEntry) {
    Date date = Date.from(logEntry.getDate());
    return DATE_FORMAT.format(date);
  }
}
