package com.ApiJuridica.ApiJuridica.Entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "acao")
public class Acao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_registro", nullable = false)
    private LocalDate dataRegistro;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "processo_id")
    private Long processoId;

    @Column(name = "tipo_acao_id")
    private Long tipoAcaoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDate dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getTipoAcao() {
        return tipoAcaoId;
    }

    public void setTipoAcao(Long tipoAcaoId) {
        this.tipoAcaoId = tipoAcaoId;
    }

    public Long getProcessoId() {
        return processoId;
    }

    public void setProcessoId(Long ProcessoId) {
        this.processoId = ProcessoId;
    }
}
