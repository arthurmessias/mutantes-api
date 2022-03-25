package com.mutantes.mutantesapi.controller;

import com.mutantes.mutantesapi.dto.DnaChainRequest;
import com.mutantes.mutantesapi.dto.StatisticRequest;
import com.mutantes.mutantesapi.exception.NonMutantException;
import com.mutantes.mutantesapi.proxy.StatisticProxy;
import com.mutantes.mutantesapi.service.IDnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Controller para validar si una cadena de ADN es MUTANTE o NO-MUTANTE
 */
@RestController
public class DnaController {

    @Autowired
    private IDnaService dnaService;

    @Autowired
    private StatisticProxy statisticProxy;

    /**
     * Valida si una cadena de ADN es mutante o no
     * @param body DTO con una cadena de ADN
     * @return 200 si es ADN de un Mutante
     * caso contrario se lanzara una exception con status 403 - No-Mutante
     */
    @PostMapping(value = "/mutant", params = "version=1")
    public ResponseEntity<String> checkMutant(@Valid @RequestBody DnaChainRequest body) {
        boolean mutant = dnaService.isMutant(body.getDna().toArray(new String[0]));

        statisticProxy.persistStatistic(new StatisticRequest(body.getDna(), mutant));

        if (!mutant)
            throw new NonMutantException("No-Mutante");

        return new ResponseEntity<String>("Mutante", HttpStatus.OK);
    }
}
