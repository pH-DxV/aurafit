package br.unitins.topicos1.aurafit.service;

import java.util.List;

import br.unitins.topicos1.aurafit.model.Sabor;
import br.unitins.topicos1.aurafit.repository.SaborRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class SaborService {

    @Inject
    SaborRepository repository;

    public List<Sabor> listarTodos() {
        return repository.listAll();
    }

    public List<Sabor> buscarPorNome(String nome) {
        return repository.buscarPorNome(nome);
    }

    public Sabor buscarPorId(Long id) {
        Sabor sabor = repository.findById(id);
        if (sabor == null) {
            throw new NotFoundException("Sabor não encontrado.");
        }
        return sabor;
    }

    @Transactional
    public Sabor inserir(Sabor sabor) {
        repository.persist(sabor);
        return sabor;
    }

    @Transactional
    public Sabor atualizar(Long id, Sabor saborAtualizado) {
        Sabor sabor = repository.findById(id);

        if (sabor == null) {
            throw new NotFoundException("Sabor não encontrado.");
        }

        sabor.setNome(saborAtualizado.getNome());
        sabor.setDescricao(saborAtualizado.getDescricao());

        return sabor;
    }

    @Transactional
    public void excluir(Long id) {
        Sabor sabor = repository.findById(id);

        if (sabor == null) {
            throw new NotFoundException("Sabor não encontrado.");
        }

        repository.delete(sabor);
    }
}