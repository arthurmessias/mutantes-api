package com.mutantes.mutantesapi.controller;

import com.mutantes.mutantesapi.dto.StatisticResponse;
import com.mutantes.mutantesapi.service.IStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller para obtener estadisticas
 */
@RestController
public class StatisticController {

    @Autowired
    private IStatisticService statisticService;

    @GetMapping(value = "/stats", params = "version=1")
    public ResponseEntity<StatisticResponse> getStatistc() {
        StatisticResponse stats = statisticService.getStatistic();

        return new ResponseEntity<StatisticResponse>(stats, HttpStatus.OK);
    }
}
