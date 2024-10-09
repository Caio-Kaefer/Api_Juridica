package com.ApiJuridica.ApiJuridica.ServicesTests;

import com.ApiJuridica.ApiJuridica.Entities.StatusProcesso;
import com.ApiJuridica.ApiJuridica.Repositories.StatusProcessoRepository;
import com.ApiJuridica.ApiJuridica.Services.StatusProcessoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class StatusProcessoServiceTests {

    @Mock
    private StatusProcessoRepository statusProcessoRepository;

    @InjectMocks
    private StatusProcessoService statusProcessoService;

    private StatusProcesso statusProcesso1;
    private StatusProcesso statusProcesso2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        statusProcesso1 = new StatusProcesso();
        statusProcesso1.setId(1L);
        statusProcesso1.setDescricao("Ativo");

        statusProcesso2 = new StatusProcesso();
        statusProcesso2.setId(2L);
        statusProcesso2.setDescricao("Inativo");
    }

    @Test
    public void getAllStatus_ReturnsAllStatuses() {

        List<StatusProcesso> statusList = Arrays.asList(statusProcesso1, statusProcesso2);
        when(statusProcessoRepository.findAll()).thenReturn(statusList);


        List<StatusProcesso> result = statusProcessoService.getAllStatus();


        assertEquals(2, result.size());
        assertEquals("Ativo", result.get(0).getDescricao());
        assertEquals("Inativo", result.get(1).getDescricao());
    }
}
