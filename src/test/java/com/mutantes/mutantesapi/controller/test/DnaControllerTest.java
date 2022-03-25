package com.mutantes.mutantesapi.controller.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mutantes.mutantesapi.controller.DnaController;
import com.mutantes.mutantesapi.dto.DnaChainRequest;
import com.mutantes.mutantesapi.service.impl.DnaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DnaController.class)
public class DnaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DnaService service;

    @Test
    public void nonMutantExceptionMessage() throws Exception {
        DnaChainRequest request = new DnaChainRequest();
        request.setDna(Arrays.asList("AA","CC"));

        ObjectMapper mapper = new ObjectMapper();

        when(service.isMutant(new String[0])).thenReturn(Boolean.FALSE);

        this.mockMvc.perform(post("/mutant")
                        .queryParam("version","1")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(request)))
                        .andExpect(status().isForbidden());
    }
}
