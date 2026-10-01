package br.unitins.topicos1.aurafit.repository;

import java.util.List;

import br.unitins.topicos1.aurafit.model.Fornecedor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FornecedorRepository implements PanacheRepository<Fornecedor> {

    public List<Fornecedor> buscarPorRazaoSocial(String razaoSocial) {
        return find("LOWER(razaoSocial) LIKE LOWER(?1)", "%" + razaoSocial + "%").list();
    }
}