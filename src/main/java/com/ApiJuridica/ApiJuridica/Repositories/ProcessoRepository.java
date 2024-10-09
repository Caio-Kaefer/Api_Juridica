package com.ApiJuridica.ApiJuridica.Repositories;

import com.ApiJuridica.ApiJuridica.Entities.Processo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Long> {

    Optional<Processo> findByNumeroProcesso(String numeroProcesso);
    Optional<List<Processo>> findByStatusProcessoId(Long processoId);



}