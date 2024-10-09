package com.ApiJuridica.ApiJuridica.Repositories;

import com.ApiJuridica.ApiJuridica.Entities.Acao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcaoRepository extends JpaRepository<Acao, Long> {
    List<Acao> findAllByProcessoId(Long ProcessoId);
}
