package br.unitins.topicos1.aurafit.service;

import java.util.List;

import br.unitins.topicos1.aurafit.model.Fornecedor;
import br.unitins.topicos1.aurafit.repository.FornecedorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class FornecedorService {

    @Inject
    FornecedorRepository repository;

    public List<Fornecedor> listarTodos() {
        return repository.listAll();
    }

    public List<Fornecedor> buscarPorRazaoSocial(String razaoSocial) {
        return repository.buscarPorRazaoSocial(razaoSocial);
    }

    public Fornecedor buscarPorId(Long id) {
        Fornecedor fornecedor = repository.findById(id);
        if (fornecedor == null) {
            throw new NotFoundException("Fornecedor não encontrado.");
        }
        return fornecedor;
    }

    @Transactional
    public Fornecedor inserir(Fornecedor fornecedor) {
        repository.persist(fornecedor);
        return fornecedor;
    }

    @Transactional
    public Fornecedor atualizar(Long id, Fornecedor fornecedorAtualizado) {
        Fornecedor fornecedor = repository.findById(id);

        if (fornecedor == null) {
            throw new NotFoundException("Fornecedor não encontrado.");
        }

        fornecedor.setRazaoSocial(fornecedorAtualizado.getRazaoSocial());
        fornecedor.setCnpj(fornecedorAtualizado.getCnpj());
        fornecedor.setTelefone(fornecedorAtualizado.getTelefone());
        fornecedor.setEmail(fornecedorAtualizado.getEmail());

        return fornecedor;
    }

    @Transactional
    public void excluir(Long id) {
        Fornecedor fornecedor = repository.findById(id);

        if (fornecedor == null) {
            throw new NotFoundException("Fornecedor não encontrado.");
        }

        repository.delete(fornecedor);
    }
}