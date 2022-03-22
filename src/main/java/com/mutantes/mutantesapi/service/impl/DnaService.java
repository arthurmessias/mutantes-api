package com.mutantes.mutantesapi.service.impl;

import com.mutantes.mutantesapi.service.IDnaService;
import com.mutantes.mutantesapi.util.MutantesUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mutantes.mutantesapi.util.MutantesUtil.Busqueda;

/**
 * Clase service para el ADN
 */
@Service
public class DnaService implements IDnaService {

    private final Logger logger = LogManager.getLogger(DnaService.class);

    /**
     * Maxima profundidad para la busqueda
     * de una secuencia de cuatro letras en la matrix
     */
    @Value(value = "${mutantes.max.depth}")
    private int maxDepth;

    /**
     * Un humano es mutante, si encuentras más de una secuencia de cuatro letras iguales
     * (en este caso, minimo 2 secuencias)
     */
    @Value(value = "${mutantes.min.accepted}")
    private int minAccepted;

    // Cada base nitrogenada del ADN: A, C, G y T
    @Value("${mutantes.dna.base}")
    private List<Character> dnaBase;

    // Numero de filas de la matrix
    private int rows = 0;

    // Numero de columnas de la matrix
    private int cols = 0;

    /**
     * Verifica si un humano es mutante, pasando su cadena de ADN
     * @param dna cadena de ADN
     * @return true Mutante, false No-Mutante
     */
    @Override
    public boolean isMutant(String[] dna) {
        rows = dna.length;
        cols = dna[0].length();

        char[][] cadena = MutantesUtil.dnaChainToMatrix(dna);

        // secuencias encontradas
        int secuencias = 0;

        for (char e : dnaBase) {
            int count;
            count = finding(cadena, e);

            // se cadena de 4 letras fue encontrada
            if (count == maxDepth)
                secuencias++;

            logger.info("{} : {}", e, count);
        }

        return (secuencias >= minAccepted);
    }

    /**
     * Busqueda por una secuencia de 4 {@code findBy} en la cadena de ADN
     * @param dna 2D char array
     * @param findBy A,T,C o G
     * @return numero de pasos hechos dentro de la matrix
     */
    private int finding(char[][] dna, char findBy) {
        int count = 0;

        // recorre la matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // se fue encontrada la secuencia de cuatro letras iguales se detiene la busqueda
                if (count == maxDepth) return count;

                // se el elemento actual de la matrix no es lo que se busca
                if (dna[i][j] != findBy) continue;

                // horizontal
                if ((j + 1 < cols) && dna[i][j + 1] == findBy) {
                    count = found(dna, findBy, i, j + 1, 0, Busqueda.HORIZONTAL);
                    continue;
                }

                // vertical
                if ((i + 1 < rows) && dna[i + 1][j] == findBy) {
                    count = found(dna, findBy, i + 1, j, 0, Busqueda.VERTICAL);
                    continue;
                }

                // oblicua
                if (((i + 1 < rows) && (j + 1 < cols)) && dna[i + 1][j + 1] == findBy) {
                    count = found(dna, findBy, i + 1, j + 1, 0, Busqueda.OBLICUA);
                    continue;
                }
            }
        }

        return count;
    }

    /**
     * Una vez encontrada la letra del ADN,
     * se empezará la busqueda por una secuencia de 4 letras iguales a {@code findBy}
     * @param dna 2D char array
     * @param findBy A,T,C o G
     * @param row fila actual
     * @param col columna actual
     * @param depth de zero hasta {@code maxDepth}
     * @param busqueda horizontal, vertical o oblicua
     * @return numero de pasos hechos dentro de la matrix
     */
    private int found(char[][] dna, char findBy, int row, int col, int depth, Busqueda busqueda) {
        logger.debug("Buscando {} {} en {} {} depth {}", findBy, busqueda, row, col, depth);

        // control del ArrayIndexOutOfBoundsException
        if (row >= rows || col >= cols)
            return 0;

        /*
        se fue encontrada la secuencia de cuatro letras iguales
        o posicion actual no es la letra buscada
        se detiene la busqueda
         */
        if (depth == maxDepth || dna[row][col] != findBy) {
            return 0;
        }

        switch (busqueda) {
            case OBLICUA:
                row++;
                col++;
                break;
            case VERTICAL:
                row++;
                break;
            case HORIZONTAL:
                col++;
                break;
        }

        depth++;

        return 1 + found(dna, findBy, row, col, depth, busqueda);
    }
}
