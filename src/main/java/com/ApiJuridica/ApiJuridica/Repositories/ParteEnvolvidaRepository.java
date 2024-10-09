package com.ApiJuridica.ApiJuridica.Repositories;

import com.ApiJuridica.ApiJuridica.Entities.ParteEnvolvida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParteEnvolvidaRepository extends JpaRepository<ParteEnvolvida, Long> {
    List<ParteEnvolvida> findAllByProcessoId(Long processoId);
    List<ParteEnvolvida> findByTipoId(Long tipoId);

}
