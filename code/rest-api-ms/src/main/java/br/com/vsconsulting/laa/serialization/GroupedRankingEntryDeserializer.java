package br.com.vsconsulting.laa.serialization;

import br.com.vsconsulting.laa.models.ranking.GroupedRankingEntry;

/**
 * Json Deserializer for @{@link GroupedRankingEntry}
 *
 * @author valverde.thiago
 */
public class GroupedRankingEntryDeserializer extends JsonPojoDeserializer<GroupedRankingEntry> {
  public GroupedRankingEntryDeserializer() {
    super(GroupedRankingEntry.class);
  }
}
