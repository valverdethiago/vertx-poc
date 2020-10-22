package br.com.vsconsulting.laa.vertx.verticles;

import br.com.vsconsulting.laa.config.ServerSettings;
import br.com.vsconsulting.laa.models.KafkaTopic;
import br.com.vsconsulting.laa.models.LogEntry;
import br.com.vsconsulting.laa.models.MetricGroupType;

import javax.inject.Inject;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * Verticle that produces aggregation by week and url
 *
 * @author valverde.thiago
 */
public class LogAggregatorByWeekVerticle extends AbstractLogAggregatorVerticle {

  @Inject
  public LogAggregatorByWeekVerticle(ServerSettings settings) {
    super(
      settings,
      MetricGroupType.GROUP_BY_WEEK,
      KafkaTopic.LOGS_INPUT,
      KafkaTopic.LOGS_GROUP_BY_WEEK_OUTPUT);
  }

  @Override
  protected String groupBy(LogEntry logRequest) {
    ZonedDateTime zdt = ZonedDateTime.ofInstant(logRequest.getDate(), ZoneId.systemDefault());
    Calendar calendar = GregorianCalendar.from(zdt);
    return calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.WEEK_OF_YEAR);
  }
}
