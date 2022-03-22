package com.mutantes.mutantesapi.service;

import com.mutantes.mutantesapi.dto.StatisticResponse;
import com.mutantes.mutantesapi.model.Statistic;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Interfaz para StatisticService
 */
public interface IStatisticService {
    @Async
    Future<Statistic> persistStatic(List<String> dnaChain, boolean isMutant);

    StatisticResponse getStatistic();
}
