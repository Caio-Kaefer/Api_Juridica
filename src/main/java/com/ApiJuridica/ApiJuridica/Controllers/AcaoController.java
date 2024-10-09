package com.ApiJuridica.ApiJuridica.Controllers;

import com.ApiJuridica.ApiJuridica.Entities.Acao;
import com.ApiJuridica.ApiJuridica.Entities.Dtos.AcaoDto;
import com.ApiJuridica.ApiJuridica.Entities.TipoAcao;
import com.ApiJuridica.ApiJuridica.Exceptions.NotFoundException;
import com.ApiJuridica.ApiJuridica.Facades.AcaoFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acoes")
@Tag(name = "Ações", description = "Operações relacionadas as Ações")
public class AcaoController {

    private final AcaoFacade acaoFacade;

    @Autowired
    public AcaoController(AcaoFacade acaoFacade) {
        this.acaoFacade = acaoFacade;
    }

    @GetMapping
    @Operation(summary = "Recupera todas as ações",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de ações recuperada com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    public ResponseEntity<List<Acao>> getAllAcoes() {
        List<Acao> acoes = acaoFacade.getAllAcoes();
        return ResponseEntity.ok(acoes);
    }

    @GetMapping("/tipos")
    @Operation(summary = "Recupera todos os tipos de ações",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de tipos de ações recuperada com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    public ResponseEntity<List<TipoAcao>> getAllTipos() {
        List<TipoAcao> tipos = acaoFacade.getAllTipoAcao();
        return ResponseEntity.ok(tipos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recupera uma ação pelo ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ação recuperada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Ação não encontrada"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    public ResponseEntity<Acao> getAcaoById(@PathVariable Long id) {
        try {
            Acao acao = acaoFacade.getAcaoById(id);
            return ResponseEntity.ok(acao);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/processo/{processoId}")
    @Operation(summary = "Recupera ações associadas a um ID de processo",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de ações recuperada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Nenhuma ação encontrada para o processo"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    public ResponseEntity<List<Acao>> getAcoesByProcessoId(@PathVariable Long processoId) {
        List<Acao> acoes;
        try {
            acoes = acaoFacade.getAcoesByProcessoId(processoId);
            return ResponseEntity.ok(acoes);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Cria uma nova ação",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Ação criada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    public ResponseEntity<Acao> createAcao(@RequestBody AcaoDto acaoDto) {
        Acao savedAcao = acaoFacade.saveAcao(acaoDto);
        return ResponseEntity.status(201).body(savedAcao);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove uma ação pelo ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Ação removida com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Ação não encontrada"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    public ResponseEntity<Void> deleteAcao(@PathVariable Long id) {
        try {
            acaoFacade.deleteAcao(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
