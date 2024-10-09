package com.ApiJuridica.ApiJuridica.ControllersTests;

import com.ApiJuridica.ApiJuridica.Controllers.ParteEnvolvidaController;
import com.ApiJuridica.ApiJuridica.Entities.Dtos.ParteEnvolvidaDto;
import com.ApiJuridica.ApiJuridica.Entities.ParteEnvolvida;
import com.ApiJuridica.ApiJuridica.Facades.ParteEnvolvidaFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
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

@WebMvcTest(ParteEnvolvidaController.class)
public class ParteEnvolvidaControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParteEnvolvidaFacade parteFacade;

    private ParteEnvolvida parteEnvolvida;
    private ParteEnvolvidaDto parteEnvolvidaDto;

    @BeforeEach
    void setUp() {
        // Initialize mock data
        parteEnvolvida = new ParteEnvolvida();
        parteEnvolvida.setId(1L);
        parteEnvolvida.setNomeCompleto("Teste Nome");
        parteEnvolvida.setCpfCnpj("12345678901");
        parteEnvolvida.setTipoId(1L);
        parteEnvolvida.setEmail("teste@teste.com");
        parteEnvolvida.setTelefone("123456789");
        parteEnvolvida.setProcessoId(1L);

        parteEnvolvidaDto = new ParteEnvolvidaDto();
        parteEnvolvidaDto.setNomeCompleto("Teste Nome");
        parteEnvolvidaDto.setCpfCnpj("12345678901");
        parteEnvolvidaDto.setTipoId(1L);
        parteEnvolvidaDto.setEmail("teste@teste.com");
        parteEnvolvidaDto.setTelefone("123456789");
        parteEnvolvidaDto.setProcessoId(1L);
    }

    @Test
    public void getAllPartesByProcessoId_ReturnsOk() throws Exception {
        List<ParteEnvolvida> partes = Arrays.asList(parteEnvolvida);
        when(parteFacade.getAllPartesByProcessoId(1L)).thenReturn(partes);

        mockMvc.perform(get("/partes/processo/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nomeCompleto").value(parteEnvolvida.getNomeCompleto()));
    }

    @Test
    public void getAllPartesByTipoId_ReturnsOk() throws Exception {
        List<ParteEnvolvida> partes = Arrays.asList(parteEnvolvida);
        when(parteFacade.getPartesByTipo(1L)).thenReturn(partes);

        mockMvc.perform(get("/partes/partes/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nomeCompleto").value(parteEnvolvida.getNomeCompleto()));
    }

    @Test
    public void createParte_ReturnsCreated() throws Exception {
        when(parteFacade.saveParte(any(ParteEnvolvidaDto.class))).thenReturn(parteEnvolvida);

        mockMvc.perform(post("/partes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nomeCompleto\":\"Teste Nome\",\"cpfCnpj\":\"12345678901\",\"tipo\":1,\"email\":\"teste@teste.com\",\"telefone\":\"123456789\",\"processo\":1}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nomeCompleto").value(parteEnvolvida.getNomeCompleto()));
    }

    @Test
    public void updateParte_ReturnsOk() throws Exception {
        when(parteFacade.updateParte(any(ParteEnvolvidaDto.class), any(Long.class))).thenReturn(parteEnvolvida);

        mockMvc.perform(put("/partes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nomeCompleto\":\"Teste Nome Atualizado\",\"cpfCnpj\":\"12345678901\",\"tipo\":1,\"email\":\"teste@teste.com\",\"telefone\":\"123456789\",\"processo\":1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomeCompleto").value(parteEnvolvida.getNomeCompleto()));
    }

    @Test
    public void deleteParte_ReturnsNoContent() throws Exception {
        mockMvc.perform(delete("/partes/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
