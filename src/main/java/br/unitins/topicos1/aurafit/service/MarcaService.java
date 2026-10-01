package br.unitins.topicos1.aurafit.service;

import java.util.List;

import br.unitins.topicos1.aurafit.model.Marca;
import br.unitins.topicos1.aurafit.repository.MarcaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class MarcaService {

    @Inject
    MarcaRepository repository;

    public List<Marca> listarTodas() {
        return repository.listAll();
    }

    public List<Marca> buscarPorNome(String nome) {
        return repository.buscarPorNome(nome);
    }

    public Marca buscarPorId(Long id) {
        Marca marca = repository.findById(id);
        if (marca == null) {
            throw new NotFoundException("Marca não encontrada.");
        }
        return marca;
    }

    @Transactional
    public Marca inserir(Marca marca) {
        repository.persist(marca);
        return marca;
    }

    @Transactional
    public Marca atualizar(Long id, Marca marcaAtualizada) {
        Marca marca = repository.findById(id);

        if (marca == null) {
            throw new NotFoundException("Marca não encontrada.");
        }

        marca.setNome(marcaAtualizada.getNome());
        marca.setCnpj(marcaAtualizada.getCnpj());
        marca.setEmailContato(marcaAtualizada.getEmailContato());

        return marca;
    }

    @Transactional
    public void excluir(Long id) {
        Marca marca = repository.findById(id);

        if (marca == null) {
            throw new NotFoundException("Marca não encontrada.");
        }

        repository.delete(marca);
    }
}