package com.ApiJuridica.ApiJuridica.Entities.Dtos;

import com.ApiJuridica.ApiJuridica.Entities.ParteEnvolvida; // Ensure you import your entity
import jakarta.persistence.*;

public class ParteEnvolvidaDto {
    private String nomeCompleto;
    private String cpfCnpj;
    private Long tipoId;
    private String email;
    private String telefone;
    private Long processoId;

    // Getters and Setters

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public Long getTipoId() {
        return tipoId;
    }

    public void setTipoId(Long tipoId) {
        this.tipoId = tipoId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


    public void setProcessoId(Long processo) {
        this.processoId = processo;
    }

    // Method to convert DTO to Entity
    public ParteEnvolvida toEntity() {
        ParteEnvolvida parteEnvolvida = new ParteEnvolvida();
        parteEnvolvida.setNomeCompleto(this.nomeCompleto);
        parteEnvolvida.setCpfCnpj(this.cpfCnpj);
        parteEnvolvida.setTipoId(this.tipoId); // Adjust as necessary based on your entity's field name
        parteEnvolvida.setEmail(this.email);
        parteEnvolvida.setTelefone(this.telefone);
        parteEnvolvida.setProcessoId(this.processoId); // Adjust as necessary based on your entity's field name
        return parteEnvolvida;
    }
}
