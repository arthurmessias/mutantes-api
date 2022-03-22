package com.mutantes.mutantesapi.repository;

import com.mutantes.mutantesapi.model.Statistic;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repositorio MongoDB para Statistic
 */
public interface StatisticRepository extends MongoRepository<Statistic, String> {

    /**
     * Conteo de ADNs MUTANTES o NO_MUTANTES
     * @param isMutant true MUTANTE, false NO-MUTANTE
     * @return
     */
    Long countByIsMutant(Boolean isMutant);
}
