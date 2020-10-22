package br.com.vsconsulting.laa.models.search;

import br.com.vsconsulting.laa.models.builders.MetricResponseWrapperBuilder;
import br.com.vsconsulting.laa.models.ranking.GroupedRankingEntry;
import br.com.vsconsulting.laa.models.ranking.RankingEntry;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;

/**
 * Used to return values for the metrics searched on the endpoint /metrics
 *
 * @author valverde.thiago
 */
@JsonInclude(Include.NON_NULL)
public class MetricResponseWrapper {

  private List<RankingEntry> rankingEntries;
  private List<GroupedRankingEntry> groupedRankingEntries;

  public MetricResponseWrapper() {
  }

  public MetricResponseWrapper(List<RankingEntry> rankingEntries, List<GroupedRankingEntry> groupedRankingEntries) {
    this.rankingEntries = rankingEntries;
    this.groupedRankingEntries = groupedRankingEntries;
  }

  public static MetricResponseWrapperBuilder builder() {
    return new MetricResponseWrapperBuilder();
  }

  public List<RankingEntry> getRankingEntries() {
    return rankingEntries;
  }

  public List<GroupedRankingEntry> getGroupedRankingEntries() {
    return groupedRankingEntries;
  }

  public void setRankingEntries(List<RankingEntry> rankingEntries) {
    this.rankingEntries = rankingEntries;
  }


  @JsonIgnore
  public boolean isEmpty() {
    return (this.rankingEntries == null || this.rankingEntries.isEmpty())
      && (this.groupedRankingEntries == null || this.groupedRankingEntries.isEmpty());
  }
}
