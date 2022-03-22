package com.mutantes.mutantesapi.service.impl;

import com.mutantes.mutantesapi.dto.StatisticResponse;
import com.mutantes.mutantesapi.model.Statistic;
import com.mutantes.mutantesapi.repository.StatisticRepository;
import com.mutantes.mutantesapi.service.IStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * Clase service para persistir las cadenas de ADN
 * y obtener estadisticas de los registros
 */
@Service
public class StatisticService implements IStatisticService {

    @Autowired
    private StatisticRepository repository;

    /**
     * Persiste cadena de ADN en la DB
     * @param dnaChain cadena de ADN
     * @param isMutant true MUTANTE, false NO-MUTANTE
     * @return objeto Statistc creado de manera asincronica
     */
    @Override
    @Async
    public Future<Statistic> persistStatic(List<String> dnaChain, boolean isMutant) {
        // id es la cadena de ADN
        String chainAppend = dnaChain.stream().collect(Collectors.joining());

        Statistic record = repository.insert(new Statistic(chainAppend, isMutant));

        return new AsyncResult(record);
    }

    /**
     * Obtener las estadisticas de las cadenas de ADN procesadas
     * @return DTO para el servicio /stats
     */
    @Override
    public StatisticResponse getStatistic() {
        Long isMutant = repository.countByIsMutant(true);
        Long nonMutant = repository.countByIsMutant(false);
        double ratio = ((double)isMutant) / nonMutant;

        return new StatisticResponse(isMutant, nonMutant, ratio);
    }
}
