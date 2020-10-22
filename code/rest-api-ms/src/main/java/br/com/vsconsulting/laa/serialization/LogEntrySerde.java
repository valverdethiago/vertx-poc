package br.com.vsconsulting.laa.serialization;

import br.com.vsconsulting.laa.models.LogEntry;

/**
 * Serde for processing @{@link LogEntry} json messages to and from kafka topics
 *
 * @author valverde.thiago
 */
public class LogEntrySerde extends JsonPojoSerde<LogEntry>{
  public LogEntrySerde() {
    super(LogEntry.class);
  }
}
