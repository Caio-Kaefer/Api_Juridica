package com.ApiJuridica.ApiJuridica.ControllersTests;

import com.ApiJuridica.ApiJuridica.Controllers.AcaoController;
import com.ApiJuridica.ApiJuridica.Entities.Acao;
import com.ApiJuridica.ApiJuridica.Entities.Dtos.AcaoDto;
import com.ApiJuridica.ApiJuridica.Facades.AcaoFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AcaoController.class)
public class AcaoControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AcaoFacade acaoFacade;

    private Acao acao;
    private AcaoDto acaoDto;

    @BeforeEach
    void setUp() {
        acao = new Acao();
        acao.setId(1L);
        acao.setDataRegistro(LocalDate.now());
        acao.setDescricao("Teste de ação");
        acao.setProcessoId(1L);
        acao.setTipoAcao(1L);

        acaoDto = new AcaoDto();
        acaoDto.setDescricao("Teste de ação");
        acaoDto.setProcessoId(1L);
        acaoDto.setTipoAcaoId(1L);
    }

    @Test
    public void getAllAcoes_ReturnsOk() throws Exception {
        List<Acao> acoes = Arrays.asList(acao);

        when(acaoFacade.getAllAcoes()).thenReturn(acoes);

        mockMvc.perform(get("/acoes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(acao.getId()))
                .andExpect(jsonPath("$[0].descricao").value(acao.getDescricao()));
    }

    @Test
    public void getAcaoById_ReturnsOk() throws Exception {
        when(acaoFacade.getAcaoById(1L)).thenReturn((acao));

        mockMvc.perform(get("/acoes/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(acao.getId()))
                .andExpect(jsonPath("$.descricao").value(acao.getDescricao()));
    }

    @Test
    public void createAcao_ReturnsCreated() throws Exception {
        when(acaoFacade.saveAcao(any(AcaoDto.class))).thenReturn(acao);

        mockMvc.perform(post("/acoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"descricao\": \"Teste de ação\", \"processoId\": 1, \"tipoAcaoId\": 1}")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.descricao").value(acao.getDescricao()));
    }

    @Test
    public void deleteAcao_ReturnsNoContent() throws Exception {
        mockMvc.perform(delete("/acoes/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
