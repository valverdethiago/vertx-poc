package br.com.vsconsulting.laa.serialization;

import br.com.vsconsulting.laa.models.ranking.RankingEntry;

/**
 * Json Deserializer for @{@link RankingEntry}
 *
 * @author valverde.thiago
 */
public class RankingEntryDeserializer extends JsonPojoDeserializer<RankingEntry> {
  public RankingEntryDeserializer() {
    super(RankingEntry.class);
  }
}
