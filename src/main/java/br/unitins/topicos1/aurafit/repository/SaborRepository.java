package br.unitins.topicos1.aurafit.repository;

import java.util.List;

import br.unitins.topicos1.aurafit.model.Sabor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SaborRepository implements PanacheRepository<Sabor> {

    public List<Sabor> buscarPorNome(String nome) {
        return find("LOWER(nome) LIKE LOWER(?1)", "%" + nome + "%").list();
    }
}