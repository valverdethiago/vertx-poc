package br.com.vsconsulting.laa.service;

import br.com.vsconsulting.laa.models.MetricGroupType;
import br.com.vsconsulting.laa.models.ranking.GroupedRankingEntry;
import br.com.vsconsulting.laa.models.ranking.RankingEntry;
import br.com.vsconsulting.laa.models.search.MetricResponseWrapper;
import br.com.vsconsulting.laa.models.search.SearchFilter;

import java.util.List;

public interface MetricCacheService {

  <T> List<T> getMetrics(MetricGroupType metricGroupType, Class<T[]> clazz);

  MetricResponseWrapper searchMetrics(SearchFilter searchFilter);

  void save(MetricGroupType metricGroupType, GroupedRankingEntry entry);

  void save(MetricGroupType metricGroupType, RankingEntry entry);

}
