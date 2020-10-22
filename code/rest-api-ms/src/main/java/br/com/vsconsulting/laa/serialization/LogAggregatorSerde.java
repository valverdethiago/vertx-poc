package br.com.vsconsulting.laa.serialization;

import br.com.vsconsulting.laa.models.LogAggregator;

/**
 * Serde for processing @{@link LogAggregator} json messages to and from kafka topics
 *
 * @author valverde.thiago
 */
public class LogAggregatorSerde extends JsonPojoSerde<LogAggregator>{
  public LogAggregatorSerde() {
    super(LogAggregator.class);
  }
}
