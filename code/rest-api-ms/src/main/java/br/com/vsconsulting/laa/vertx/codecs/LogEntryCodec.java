package br.com.vsconsulting.laa.vertx.codecs;

import br.com.vsconsulting.laa.models.LogEntry;

/**
 * Vert.x code for (de)serialization of @{@link LogEntry}
 *
 * @author valverde.thiago
 */
public class LogEntryCodec extends JsonPojoCodec<LogEntry> {
  public LogEntryCodec() {
    super(LogEntry.class);
  }
}
