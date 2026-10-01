package br.unitins.topicos1.aurafit.service;

import java.util.List;

import br.unitins.topicos1.aurafit.model.Categoria;
import br.unitins.topicos1.aurafit.repository.CategoriaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class CategoriaService {

    @Inject
    CategoriaRepository repository;

    public List<Categoria> listarTodas() {
        return repository.listAll();
    }

    public List<Categoria> buscarPorNome(String nome) {
        return repository.buscarPorNome(nome);
    }

    public Categoria buscarPorId(Long id) {
        Categoria categoria = repository.findById(id);
        if (categoria == null) {
            throw new NotFoundException("Categoria não encontrada.");
        }
        return categoria;
    }

    @Transactional
    public Categoria inserir(Categoria categoria) {
        repository.persist(categoria);
        return categoria;
    }

    @Transactional
    public Categoria atualizar(Long id, Categoria categoriaAtualizada) {
        Categoria categoria = repository.findById(id);

        if (categoria == null) {
            throw new NotFoundException("Categoria não encontrada.");
        }

        categoria.setNome(categoriaAtualizada.getNome());
        categoria.setDescricao(categoriaAtualizada.getDescricao());

        return categoria;
    }

    @Transactional
    public void excluir(Long id) {
        Categoria categoria = repository.findById(id);

        if (categoria == null) {
            throw new NotFoundException("Categoria não encontrada.");
        }

        repository.delete(categoria);
    }
}