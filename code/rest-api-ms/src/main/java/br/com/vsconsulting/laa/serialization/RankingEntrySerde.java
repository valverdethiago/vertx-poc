package br.com.vsconsulting.laa.serialization;

import br.com.vsconsulting.laa.models.ranking.RankingEntry;

/**
 * Serde for processing @{@link RankingEntry} json messages to and from kafka topics
 *
 * @author valverde.thiago
 */
public class RankingEntrySerde extends JsonPojoSerde<RankingEntry>{
  public RankingEntrySerde() {
    super(RankingEntry.class);
  }
}
