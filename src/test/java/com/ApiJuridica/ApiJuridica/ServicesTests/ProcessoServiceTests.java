package com.ApiJuridica.ApiJuridica.ServicesTests;

import com.ApiJuridica.ApiJuridica.Entities.Dtos.ProcessoDto;
import com.ApiJuridica.ApiJuridica.Entities.Dtos.ProcessoUpdateDto;
import com.ApiJuridica.ApiJuridica.Entities.Processo;
import com.ApiJuridica.ApiJuridica.Repositories.ProcessoRepository;
import com.ApiJuridica.ApiJuridica.Services.ProcessoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProcessoServiceTests {

    @Mock
    private ProcessoRepository processoRepository;

    @InjectMocks
    private ProcessoService processoService;

    private Processo processo;
    private ProcessoDto processoDto;
    private ProcessoUpdateDto processoUpdateDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        processo = new Processo();
        processo.setId(1L);
        processo.setNumeroProcesso("20231009123456");
        processo.setDataAbertura(LocalDate.now());

        processoDto = new ProcessoDto();
        processoDto.setDescricao("TESTE");
        processoDto.setStatusProcessoId(1L);

        processoUpdateDto = new ProcessoUpdateDto();
    }

    @Test
    public void findByNumeroProcesso_ReturnsProcesso() {
        when(processoRepository.findByNumeroProcesso("20231009123456")).thenReturn(Optional.of(processo));
        Optional<Processo> result = processoService.findByNumeroProcesso("20231009123456");
        assertTrue(result.isPresent());
        assertEquals(processo.getNumeroProcesso(), result.get().getNumeroProcesso());
    }

    @Test
    public void findAll_ReturnsProcessos() {
        when(processoRepository.findAll()).thenReturn(Arrays.asList(processo));
        List<Processo> result = processoService.findAll();
        assertEquals(1, result.size());
        assertEquals(processo.getNumeroProcesso(), result.get(0).getNumeroProcesso());
    }

    @Test
    public void save_SuccessfullySavesProcesso() {


        when(processoRepository.save(any(Processo.class))).thenReturn(processo);
        Processo result = processoService.save(processoDto);
        assertNotNull(result);
        assertEquals(processo.getId(), result.getId());
        assertEquals(processo.getNumeroProcesso(), result.getNumeroProcesso());
    }

    @Test
    public void update_SuccessfullyUpdatesProcesso() {
        when(processoRepository.existsById(1L)).thenReturn(true);
        when(processoRepository.save(any(Processo.class))).thenReturn(processo);
        Processo result = processoService.update(processoUpdateDto, 1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void update_NotFound() {
        when(processoRepository.existsById(1L)).thenReturn(false);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            processoService.update(processoUpdateDto, 1L);
        });

        assertEquals("Processo com  ID 1 não encontrado.", thrown.getMessage());
    }

    @Test
    public void deleteById_SuccessfullyDeletesProcesso() {
        when(processoRepository.existsById(1L)).thenReturn(true);
        processoService.deleteById(1L);
        verify(processoRepository, times(1)).deleteById(1L);
    }

    @Test
    public void deleteById_NotFound() {
        when(processoRepository.existsById(1L)).thenReturn(false);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            processoService.deleteById(1L);
        });

        assertEquals("Processo com  ID 1 não encontrado.", thrown.getMessage());
    }

    @Test
    public void findByStatusProcessoId_ReturnsProcessos() {
        when(processoRepository.findByStatusProcessoId(1L)).thenReturn(Optional.of(Arrays.asList(processo)));
        Optional<List<Processo>> result = processoService.findByStatusProcessoId(1L);
        assertTrue(result.isPresent());
        assertEquals(1, result.get().size());
        assertEquals(processo.getNumeroProcesso(), result.get().get(0).getNumeroProcesso());
    }
}
