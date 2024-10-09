package com.ApiJuridica.ApiJuridica.Controllers;

import com.ApiJuridica.ApiJuridica.Entities.Dtos.ParteEnvolvidaDto;
import com.ApiJuridica.ApiJuridica.Entities.ParteEnvolvida;
import com.ApiJuridica.ApiJuridica.Exceptions.NotFoundException;
import com.ApiJuridica.ApiJuridica.Facades.ParteEnvolvidaFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("partes")
@Tag(name = "Partes Envolvidas", description = "Operações relacionadas as Partes envolvidas")
public class ParteEnvolvidaController {

    private final ParteEnvolvidaFacade parteFacade;

    @Autowired
    public ParteEnvolvidaController(ParteEnvolvidaFacade parteFacade) {
        this.parteFacade = parteFacade;
    }

    @GetMapping("/processo/{processoId}")
    @Operation(summary = "Recupera todas as partes envolvidas por ID do processo",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de partes recuperada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Nenhuma parte encontrada para o processo"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    public ResponseEntity<List<ParteEnvolvida>> getAllPartesByProcessoId(@PathVariable Long processoId) {
        List<ParteEnvolvida> partes = parteFacade.getAllPartesByProcessoId(processoId);
        if (partes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(partes);
    }

    @GetMapping("/tipos/{tipoId}")
    @Operation(summary = "Recupera todas as partes envolvidas por ID do tipo",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de partes recuperada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Nenhuma parte encontrada para o tipo"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    public ResponseEntity<List<ParteEnvolvida>> getAllPartesByTipoId(@PathVariable Long tipoId) {
        List<ParteEnvolvida> partes = parteFacade.getPartesByTipo(tipoId);
        if (partes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(partes);
    }

    @PostMapping
    @Operation(summary = "Cria uma nova parte envolvida",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Parte criada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    public ResponseEntity<ParteEnvolvida> createParte(@RequestBody ParteEnvolvidaDto parteEnvolvidaDto) {
        ParteEnvolvida createdParte = parteFacade.saveParte(parteEnvolvidaDto);
        return ResponseEntity.status(201).body(createdParte);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma parte envolvida pelo ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Parte atualizada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Parte não encontrada"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    public ResponseEntity<ParteEnvolvida> updateParte(@PathVariable Long id, @RequestBody ParteEnvolvidaDto parteEnvolvidaDto) {
        try {
            ParteEnvolvida updatedParte = parteFacade.updateParte(parteEnvolvidaDto, id);
            return ResponseEntity.ok(updatedParte);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove uma parte envolvida pelo ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Parte removida com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Parte não encontrada"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    public ResponseEntity<Void> deleteParte(@PathVariable Long id) {
        try {
            parteFacade.deleteParte(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
