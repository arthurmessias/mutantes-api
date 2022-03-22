package com.mutantes.mutantesapi.util;

import lombok.experimental.UtilityClass;

/**
 * Clase Util para Magneto
 */
@UtilityClass
public final class MutantesUtil {

    /**
     * Tipos de busqueda que se harán
     */
    public enum Busqueda {HORIZONTAL, VERTICAL, OBLICUA;}

    /**
     * Transforma cadenas de ADN en una matrix de char array
     * @param chain cadena ADN compuesta por letras A,T,C,G
     * @return 2D char array
     */
    public char[][] dnaChainToMatrix(String[] chain) {
        int rows = chain.length;
        char[][] matrix = new char[rows][];

        for (int i = 0; i < rows; i ++)
            matrix[i] = chain[i].toCharArray();

        return matrix;
    }
}
