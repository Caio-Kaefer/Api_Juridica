package com.ApiJuridica.ApiJuridica.Entities.Dtos;

import com.ApiJuridica.ApiJuridica.Entities.Acao;

import java.time.LocalDate;

public class AcaoDto {

    private String descricao;
    private Long processoId;
    private Long tipoAcaoId;

    // Getters and Setters

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getProcessoId() {
        return processoId;
    }

    public void setProcessoId(Long processoId) {
        this.processoId = processoId;
    }

    public Long getTipoAcaoId() {
        return tipoAcaoId;
    }

    public void setTipoAcaoId(Long tipoAcaoId) {
        this.tipoAcaoId = tipoAcaoId;
    }

    public Acao toEntity() {
        Acao acao = new Acao();
        acao.setDataRegistro(LocalDate.now());
        acao.setDescricao(this.descricao);
        acao.setProcessoId(this.processoId);
        acao.setTipoAcao(this.tipoAcaoId);
        return acao;
    }

}
