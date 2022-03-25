package com.mutantes.mutantesapi.proxy;

import com.mutantes.mutantesapi.dto.StatisticRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Cliente Feign para comunicarse con la DB
 */
@FeignClient(name="mutantes-db-api")
public interface StatisticProxy {

    /**
     * Request async para persistir una cadena ADN
     * @param body
     * @return
     */
    @Async
    @PostMapping(value = "/mutantes-db-api/statistic?version=1")
    public ResponseEntity<StatisticRequest> persistStatistic(@RequestBody StatisticRequest body);
}
