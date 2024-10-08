package com.ApiJuridica.ApiJuridica.Repositories;

import com.ApiJuridica.ApiJuridica.Entities.Processo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProcessoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // Method to save a Processo
    public void save(Processo processo) {
        entityManager.persist(processo);
    }

    // Method to find a Processo by its numeroProcesso
    public Optional<Processo> findByNumeroProcesso(String numeroProcesso) {
        Query query = entityManager.createQuery("SELECT p FROM Processo p WHERE p.numeroProcesso = :numeroProcesso", Processo.class);
        query.setParameter("numeroProcesso", numeroProcesso);
        return query.getResultStream().findFirst();
    }

    // Method to find a Processo by its descricao (as an example of another query)
    public List findByDescricao(String descricao) {
        Query query = entityManager.createQuery("SELECT p FROM Processo p WHERE p.descricao LIKE :descricao", Processo.class);
        query.setParameter("descricao", "%" + descricao + "%");
        return query.getResultList();
    }

    // Method to find all Processos
    public List<Processo> findAll() {
        return entityManager.createQuery("SELECT p FROM Processo p", Processo.class).getResultList();
    }

    // Method to delete a Processo
    public void delete(Processo processo) {
        entityManager.remove(entityManager.contains(processo) ? processo : entityManager.merge(processo));
    }
}
