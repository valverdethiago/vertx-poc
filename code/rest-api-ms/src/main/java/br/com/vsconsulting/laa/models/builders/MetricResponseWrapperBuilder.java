package br.com.vsconsulting.laa.models.builders;

import br.com.vsconsulting.laa.models.search.MetricResponseWrapper;
import br.com.vsconsulting.laa.models.ranking.GroupedRankingEntry;
import br.com.vsconsulting.laa.models.ranking.RankingEntry;

import java.util.List;

/**
 * Metrics for @{@link MetricResponseWrapper}
 *
 * @author valverde.thiago
 */
public class MetricResponseWrapperBuilder {
    private List<RankingEntry> rankingEntries;
    private List<GroupedRankingEntry> groupedRankingEntries;

    public MetricResponseWrapperBuilder rankingEntrie(List<RankingEntry> rankingEntrie) {
        this.rankingEntries = rankingEntrie;
        return this;
    }

    public MetricResponseWrapperBuilder groupedRankingEntries(List<GroupedRankingEntry> groupedRankingEntries) {
        this.groupedRankingEntries = groupedRankingEntries;
        return this;
    }

    public MetricResponseWrapper build() {
        return new MetricResponseWrapper(rankingEntries, groupedRankingEntries);
    }
}
