package com.ApiJuridica.ApiJuridica.ServicesTests;

import com.ApiJuridica.ApiJuridica.Entities.TipoAcao;
import com.ApiJuridica.ApiJuridica.Repositories.TipoAcaoRepository;
import com.ApiJuridica.ApiJuridica.Services.TipoAcaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TipoAcaoServiceTests {

    @Mock
    private TipoAcaoRepository tipoAcaoRepository;

    @InjectMocks
    private TipoAcaoService tipoAcaoService;

    private TipoAcao tipoAcao1;
    private TipoAcao tipoAcao2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        tipoAcao1 = new TipoAcao();
        tipoAcao1.setId(1L);
        tipoAcao1.setDescricao("Tipo Ação 1");

        tipoAcao2 = new TipoAcao();
        tipoAcao2.setId(2L);
        tipoAcao2.setDescricao("Tipo Ação 2");
    }

    @Test
    public void getAll_ReturnsAllTipoAcao() {
        List<TipoAcao> tipoAcaoList = Arrays.asList(tipoAcao1, tipoAcao2);
        when(tipoAcaoRepository.findAll()).thenReturn(tipoAcaoList);

        List<TipoAcao> result = tipoAcaoService.getAll();

        assertEquals(2, result.size());
        assertEquals("Tipo Ação 1", result.get(0).getDescricao());
        assertEquals("Tipo Ação 2", result.get(1).getDescricao());
    }
}
