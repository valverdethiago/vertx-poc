package br.com.vsconsulting.laa.vertx.codecs;

import br.com.vsconsulting.laa.models.ranking.RankingEntry;

/**
 * Vert.x code for (de)serialization of @{@link RankingEntry}
 *
 * @author valverde.thiago
 */
public class RankingEntryCodec extends  JsonPojoCodec<RankingEntry> {

  public RankingEntryCodec() {
    super(RankingEntry.class);
  }

}
