package com.ApiJuridica.ApiJuridica.Controllers;

import com.ApiJuridica.ApiJuridica.Entities.Acao;
import com.ApiJuridica.ApiJuridica.Facades.AcaoFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/acoes")
public class AcaoController {

    private final AcaoFacade acaoFacade;

    @Autowired
    public AcaoController(AcaoFacade acaoFacade) {
        this.acaoFacade = acaoFacade;
    }

    @GetMapping
    public ResponseEntity<List<Acao>> getAllAcoes() {
        List<Acao> acoes = acaoFacade.getAllAcoes();
        return ResponseEntity.ok(acoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Acao> getAcaoById(@PathVariable Long id) {
        Optional<Acao> acao = acaoFacade.getAcaoById(id);
        return acao.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Acao> createAcao(@RequestBody Acao acao) {
        Acao savedAcao = acaoFacade.saveAcao(acao);
        return ResponseEntity.ok(savedAcao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAcao(@PathVariable Long id) {
        acaoFacade.deleteAcao(id);
        return ResponseEntity.noContent().build();
    }
}
