package com.ApiJuridica.ApiJuridica.ServicesTests;

import com.ApiJuridica.ApiJuridica.Entities.Dtos.ParteEnvolvidaDto;
import com.ApiJuridica.ApiJuridica.Entities.ParteEnvolvida;
import com.ApiJuridica.ApiJuridica.Repositories.ParteEnvolvidaRepository;
import com.ApiJuridica.ApiJuridica.Services.ParteEnvolvidaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ParteEnvolvidaServiceTests {

    @Mock
    private ParteEnvolvidaRepository parteRepository;

    @InjectMocks
    private ParteEnvolvidaService parteEnvolvidaService;

    private ParteEnvolvida parteEnvolvida;
    private ParteEnvolvidaDto parteEnvolvidaDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
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
    public void getAllPartesByProcessoId_ReturnsPartes() {
        when(parteRepository.findAllByProcessoId(1L)).thenReturn(Arrays.asList(parteEnvolvida));
        List<ParteEnvolvida> result = parteEnvolvidaService.getAllPartesByProcessoId(1L);
        assertEquals(1, result.size());
        assertEquals("Teste Nome", result.get(0).getNomeCompleto());
    }

    @Test
    public void saveParteEnvolvida_SuccessfullySaves() {
        when(parteRepository.save(any(ParteEnvolvida.class))).thenReturn(parteEnvolvida);
        ParteEnvolvida result = parteEnvolvidaService.saveParteEnvolvida(parteEnvolvidaDto);
        assertNotNull(result);
        assertEquals("Teste Nome", result.getNomeCompleto());
    }

    @Test
    public void updateParteEnvolvida_SuccessfullyUpdates() {
        when(parteRepository.existsById(1L)).thenReturn(true);
        when(parteRepository.save(any(ParteEnvolvida.class))).thenReturn(parteEnvolvida);
        ParteEnvolvida result = parteEnvolvidaService.updateParteEnvolvida(parteEnvolvidaDto, 1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void updateParteEnvolvida_NotFound() {
        when(parteRepository.existsById(1L)).thenReturn(false);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            parteEnvolvidaService.updateParteEnvolvida(parteEnvolvidaDto, 1L);
        });
        assertEquals("Parte Envolvida  com ID 1 não encontrada.", thrown.getMessage());
    }

    @Test
    public void deleteParteEnvolvida_SuccessfullyDeletes() {
        parteEnvolvidaService.deleteParteEnvolvida(1L);
        verify(parteRepository, times(1)).deleteById(1L);
    }

    @Test
    public void getPartesByTipo_ReturnsPartes() {
        when(parteRepository.findByTipoId(1L)).thenReturn(Arrays.asList(parteEnvolvida));
        List<ParteEnvolvida> result = parteEnvolvidaService.getPartesByTipo(1L);
        assertEquals(1, result.size());
        assertEquals("Teste Nome", result.get(0).getNomeCompleto());
    }
}
