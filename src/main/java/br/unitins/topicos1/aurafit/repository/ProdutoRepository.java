package br.unitins.topicos1.aurafit.repository;

import java.util.List;

import br.unitins.topicos1.aurafit.model.Produto;
import br.unitins.topicos1.aurafit.model.TipoProteina;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProdutoRepository implements PanacheRepository<Produto> {

    public List<Produto> buscarPorNome(String nome) {
        return find("LOWER(nome) LIKE LOWER(?1)", "%" + nome + "%").list();
    }

    public List<Produto> buscarPorTipoProteina(TipoProteina tipoProteina) {
        return find("tipoProteina", tipoProteina).list();
    }
}