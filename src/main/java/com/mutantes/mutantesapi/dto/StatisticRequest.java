package com.mutantes.mutantesapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticRequest {
    private List<String> dna;
    private Boolean isMutant;
}
