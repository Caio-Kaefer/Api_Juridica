package com.ApiJuridica.ApiJuridica.Controllers;

import com.ApiJuridica.ApiJuridica.Entities.Dtos.ProcessoDto;
import com.ApiJuridica.ApiJuridica.Entities.Dtos.ProcessoUpdateDto;
import com.ApiJuridica.ApiJuridica.Entities.Processo;
import com.ApiJuridica.ApiJuridica.Entities.StatusProcesso;
import com.ApiJuridica.ApiJuridica.Exceptions.NotFoundException;
import com.ApiJuridica.ApiJuridica.Facades.ProcessoFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/processos")
@Tag(name = "Processos", description = "Operações relacionadas aos processos")
public class ProcessoController {

    private final ProcessoFacade processoFacade;

    @Autowired
    public ProcessoController(ProcessoFacade processoFacade) {
        this.processoFacade = processoFacade;
    }

    @GetMapping("/numero-processo/{numeroProcesso}")
    @Operation(summary = "Recupera um processo pelo número",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Processo encontrado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Processo não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    public ResponseEntity<Processo> getProcessoByNumero(@PathVariable String numeroProcesso) {
        Optional<Processo> processo = this.processoFacade.getProcessoByNumero(numeroProcesso);
        return processo.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Recupera todos os processos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de processos recuperada com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    public ResponseEntity<List<Processo>> getAllProcessos() {
        List<Processo> processos = this.processoFacade.getAllProcessos();
        return ResponseEntity.ok(processos);
    }

    @GetMapping("/{statusId}")
    @Operation(summary = "Recupera processos por ID de status",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de processos recuperada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Nenhum processo encontrado para o status"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    public ResponseEntity<Optional<List<Processo>>> getProcessoByStatus(@PathVariable Long statusId) {
        Optional<List<Processo>> processos = this.processoFacade.getProcessoByStatus(statusId);
        return ResponseEntity.ok(processos);
    }

    @PostMapping
    @Operation(summary = "Cria um novo processo",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Processo criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    public ResponseEntity<Processo> createProcesso(@RequestBody ProcessoDto processo) {
        try {
            Processo savedProcesso = this.processoFacade.saveProcesso(processo);
            return ResponseEntity.status(201).body(savedProcesso);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um processo pelo ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Processo removido com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Processo não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    public ResponseEntity<Void> deleteProcesso(@PathVariable Long id) {
        try {
            processoFacade.deleteProcesso(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/status")
    @Operation(summary = "Recupera todos os status de processo",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de status recuperada com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    public ResponseEntity<List<StatusProcesso>> getAllStatus() {
        List<StatusProcesso> statusProcessos = processoFacade.getAllStatus();
        return ResponseEntity.ok(statusProcessos);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um processo pelo ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Processo atualizado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Processo não encontrado"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    public ResponseEntity<Processo> updateProcesso(@PathVariable Long id, @RequestBody ProcessoUpdateDto processoDto) {
        try {
            Processo updatedProcesso = this.processoFacade.updateProcesso(id, processoDto);
            return ResponseEntity.ok(updatedProcesso);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
