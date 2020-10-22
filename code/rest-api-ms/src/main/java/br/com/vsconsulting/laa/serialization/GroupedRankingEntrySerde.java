package br.com.vsconsulting.laa.serialization;

import br.com.vsconsulting.laa.models.ranking.GroupedRankingEntry;

/**
 * Serde for processing @{@link GroupedRankingEntry} json messages to and from kafka topics
 *
 * @author valverde.thiago
 */
public class GroupedRankingEntrySerde extends JsonPojoSerde<GroupedRankingEntry>{
  public GroupedRankingEntrySerde() {
    super(GroupedRankingEntry.class);
  }
}
