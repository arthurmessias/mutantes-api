package com.mutantes.mutantesapi.service.test;

import com.mutantes.mutantesapi.service.impl.DnaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = DnaService.class)
public class DnaServiceTest {

    @Mock
    private DnaService service;

    @BeforeEach
    void setMockOutput() {
        when(service.isMutant(new String[] {"AAAATT","CCCCTT"})).thenReturn(Boolean.TRUE);
    }

    @Test
    public void whenIsMutant() {
        assertTrue(service.isMutant(new String[] {"AAAATT","CCCCTT"}));
    }
}
