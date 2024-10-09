package com.ApiJuridica.ApiJuridica.ServicesTests;

import com.ApiJuridica.ApiJuridica.Entities.TipoParteEnvolvida;
import com.ApiJuridica.ApiJuridica.Repositories.TIpoParteEnvolvidaRepository;
import com.ApiJuridica.ApiJuridica.Services.TipoParteEnvolvidaService;
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

public class TipoParteEnvolvidaServiceTests {

    @Mock
    private TIpoParteEnvolvidaRepository tipoParteEnvolvidaRepository;

    @InjectMocks
    private TipoParteEnvolvidaService tipoParteEnvolvidaService;

    private TipoParteEnvolvida tipoParteEnvolvida1;
    private TipoParteEnvolvida tipoParteEnvolvida2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        tipoParteEnvolvida1 = new TipoParteEnvolvida();
        tipoParteEnvolvida1.setId(1L);
        tipoParteEnvolvida1.setDescricao("Tipo Parte 1");

        tipoParteEnvolvida2 = new TipoParteEnvolvida();
        tipoParteEnvolvida2.setId(2L);
        tipoParteEnvolvida2.setDescricao("Tipo Parte 2");
    }

    @Test
    public void findAll_ReturnsAllTipoParteEnvolvida() {
        List<TipoParteEnvolvida> tipoParteEnvolvidaList = Arrays.asList(tipoParteEnvolvida1, tipoParteEnvolvida2);
        when(tipoParteEnvolvidaRepository.findAll()).thenReturn(tipoParteEnvolvidaList);

        List<TipoParteEnvolvida> result = tipoParteEnvolvidaService.findAll();

        assertEquals(2, result.size()); // Ensure the size matches
        assertEquals("Tipo Parte 1", result.get(0).getDescricao());
        assertEquals("Tipo Parte 2", result.get(1).getDescricao());
    }

    @Test
    public void findById_ReturnsTipoParteEnvolvida() {
        when(tipoParteEnvolvidaRepository.findById(1L)).thenReturn(Optional.of(tipoParteEnvolvida1));

        Optional<TipoParteEnvolvida> result = tipoParteEnvolvidaService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("Tipo Parte 1", result.get().getDescricao());
    }

    @Test
    public void save_SuccessfullySavesTipoParteEnvolvida() {
        when(tipoParteEnvolvidaRepository.save(any(TipoParteEnvolvida.class))).thenReturn(tipoParteEnvolvida1);
        TipoParteEnvolvida result = tipoParteEnvolvidaService.save(tipoParteEnvolvida1);
        assertNotNull(result);
        assertEquals("Tipo Parte 1", result.getDescricao());
    }

    @Test
    public void deleteById_CallsRepositoryDelete() {
        Long idToDelete = 1L;

        tipoParteEnvolvidaService.deleteById(idToDelete);

        verify(tipoParteEnvolvidaRepository, times(1)).deleteById(idToDelete);
    }
}
