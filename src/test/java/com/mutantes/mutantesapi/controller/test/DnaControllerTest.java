package com.mutantes.mutantesapi.controller.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mutantes.mutantesapi.controller.DnaController;
import com.mutantes.mutantesapi.dto.DnaChainRequest;
import com.mutantes.mutantesapi.proxy.StatisticProxy;
import com.mutantes.mutantesapi.service.IDnaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableWebMvc
@AutoConfigureMockMvc
@SpringBootTest(classes = DnaController.class)
public class DnaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatisticProxy proxy;

    @MockBean
    private IDnaService service;

    @BeforeEach
    void setMockOutput() {
        when(service.isMutant(new String[0])).thenReturn(Boolean.FALSE);
    }

    @Test
    public void nonMutantExceptionMessage() throws Exception {
        DnaChainRequest request = new DnaChainRequest();
        request.setDna(Arrays.asList("AA","CC"));

        ObjectMapper mapper = new ObjectMapper();

        this.mockMvc.perform(post("/mutant")
                        .queryParam("version","1")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(request)))
                        .andDo(print())
                        .andExpect(status().isForbidden());
    }
}
