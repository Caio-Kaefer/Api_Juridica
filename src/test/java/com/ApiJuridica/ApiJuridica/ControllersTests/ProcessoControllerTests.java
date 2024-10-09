package com.ApiJuridica.ApiJuridica.ControllersTests;

import com.ApiJuridica.ApiJuridica.Controllers.ProcessoController;
import com.ApiJuridica.ApiJuridica.Entities.Dtos.ProcessoDto;
import com.ApiJuridica.ApiJuridica.Entities.Processo;
import com.ApiJuridica.ApiJuridica.Facades.ProcessoFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProcessoController.class)
public class ProcessoControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProcessoFacade processoFacade;

    private Processo processo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        processo = new Processo();
        processo.setId(1L);
        processo.setNumeroProcesso("12345");
    }

    @Test
    public void getProcessoByNumero_ReturnsOk() throws Exception {
        when(processoFacade.getProcessoByNumero("12345")).thenReturn(Optional.of(processo));

        mockMvc.perform(get("/processos/numero-processo/12345"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numeroProcesso").value(processo.getNumeroProcesso()));
    }

    @Test
    public void getProcessoByNumero_ReturnsNotFound() throws Exception {
        when(processoFacade.getProcessoByNumero("nonexistent")).thenReturn(Optional.empty());

        mockMvc.perform(get("/processos/numero-processo/nonexistent"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllProcessos_ReturnsOk() throws Exception {
        List<Processo> processos = Arrays.asList(processo);
        when(processoFacade.getAllProcessos()).thenReturn(processos);

        mockMvc.perform(get("/processos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].numeroProcesso").value(processo.getNumeroProcesso()));
    }

    @Test
    public void createProcesso_ReturnsCreated() throws Exception {
        when(processoFacade.saveProcesso(any(ProcessoDto.class))).thenReturn(processo);

        mockMvc.perform(post("/processos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"numero\":\"12345\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.numeroProcesso").value(processo.getNumeroProcesso()));
    }

    @Test
    public void deleteProcesso_ReturnsNoContent() throws Exception {
        mockMvc.perform(delete("/processos/1"))
                .andExpect(status().isNoContent());
    }
}
