package br.com.vsconsulting.laa.vertx.codecs;

import br.com.vsconsulting.laa.models.ranking.GroupedRankingEntry;

/**
 * Vert.x codec for (de)serializing @{@link GroupedRankingEntry}
 *
 * @author valverde.thiago
 */
public class GroupedRankingEntryCodec extends JsonPojoCodec<GroupedRankingEntry> {
  public GroupedRankingEntryCodec() {
    super(GroupedRankingEntry.class);
  }
}
