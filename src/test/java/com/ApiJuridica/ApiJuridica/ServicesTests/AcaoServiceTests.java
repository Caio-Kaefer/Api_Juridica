package com.ApiJuridica.ApiJuridica.ServicesTests;

import com.ApiJuridica.ApiJuridica.Entities.Acao;
import com.ApiJuridica.ApiJuridica.Entities.Dtos.AcaoDto;
import com.ApiJuridica.ApiJuridica.Repositories.AcaoRepository;
import com.ApiJuridica.ApiJuridica.Services.AcaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AcaoServiceTests {

    @Mock
    private AcaoRepository acaoRepository;

    @InjectMocks
    private AcaoService acaoService;

    private Acao acao;
    private AcaoDto acaoDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        acao = new Acao();
        acao.setId(1L);
        acao.setDescricao("Descrição da Ação");

        acaoDto = new AcaoDto();
        acaoDto.setDescricao("Ação Teste");
    }

    @Test
    public void getAllAcoes_ReturnsAcoes() {
        // Dado que o repositório retorna uma lista de ações
        when(acaoRepository.findAll()).thenReturn(Arrays.asList(acao));

        // Quando chamamos o serviço
        List<Acao> result = acaoService.getAllAcoes();

        // Então verificamos se o serviço retorna a lista corretamente
        assertEquals(1, result.size());
        assertEquals("Descrição da Ação", result.get(0).getDescricao());
    }

    @Test
    public void getAcaoById_ReturnsAcao() {
        when(acaoRepository.findById(1L)).thenReturn(Optional.of(acao));
        Acao result = acaoService.getAcaoById(1L);
        assertEquals("Descrição da Ação", result.getDescricao());
    }

    @Test
    public void getAcaoById_NotFound() {
        when(acaoRepository.findById(1L)).thenReturn(Optional.empty());
        Acao result = acaoService.getAcaoById(1L);
    }

    @Test
    public void saveAcao_SuccessfullySaves() {
        when(acaoRepository.save(any(Acao.class))).thenReturn(acao);
        Acao result = acaoService.saveAcao(acaoDto);
        assertNotNull(result);
        assertEquals("Descrição da Ação", result.getDescricao());
    }

    @Test
    public void deleteAcao_SuccessfullyDeletes() {
        acaoService.deleteAcao(1L);
        verify(acaoRepository, times(1)).deleteById(1L);
    }
}
