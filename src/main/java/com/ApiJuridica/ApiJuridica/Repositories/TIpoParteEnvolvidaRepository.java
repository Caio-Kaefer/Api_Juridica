package com.ApiJuridica.ApiJuridica.Repositories;

import com.ApiJuridica.ApiJuridica.Entities.TipoParteEnvolvida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TIpoParteEnvolvidaRepository extends JpaRepository<TipoParteEnvolvida, Long> {
}
