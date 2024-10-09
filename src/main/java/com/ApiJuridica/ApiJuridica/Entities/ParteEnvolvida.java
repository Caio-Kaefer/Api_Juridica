package com.ApiJuridica.ApiJuridica.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "parte_envolvida")
public class ParteEnvolvida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_completo", nullable = false)
    private String nomeCompleto;

    @Column(name = "cpf_cnpj", unique = true, nullable = false)
    private String cpfCnpj;

    @Column(name = "tipo_id", nullable = false)
    private Long tipoId;  // Example values: "autor", "réu", "advogado"

    @Column(name = "contato_email")
    private String email;

    @Column(name = "contato_telefone")
    private String telefone;

    @Column(name = "processo_id", nullable = false)  // Storing just the foreign key as a Long
    private Long processoId;

    public ParteEnvolvida() {}

    public ParteEnvolvida(String nomeCompleto, String cpfCnpj, Long tipoId, String email, String telefone, Long processoId) {
        this.nomeCompleto = nomeCompleto;
        this.cpfCnpj = cpfCnpj;
        this.tipoId = tipoId;
        this.email = email;
        this.telefone = telefone;
        this.processoId = processoId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getProcessoId() {
        return this.processoId;
    }

    public void setProcessoId(Long processo) {
        this.processoId = processo;
    }
}
