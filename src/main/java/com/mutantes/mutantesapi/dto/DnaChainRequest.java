package com.mutantes.mutantesapi.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * DTO para recibir una cadena de ADN
 */
@Data
public class DnaChainRequest {
    @NotEmpty
    private List<String> dna;
}
