package br.unitins.topicos1.aurafit.service;

import java.util.List;

import br.unitins.topicos1.aurafit.model.Produto;
import br.unitins.topicos1.aurafit.model.TipoProteina;
import br.unitins.topicos1.aurafit.repository.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ProdutoService {

    @Inject
    ProdutoRepository repository;

    public List<Produto> listarTodos() {
        return repository.listAll();
    }

    public List<Produto> buscarPorNome(String nome) {
        return repository.buscarPorNome(nome);
    }

    public List<Produto> buscarPorTipoProteina(TipoProteina tipoProteina) {
        return repository.buscarPorTipoProteina(tipoProteina);
    }

    public Produto buscarPorId(Long id) {
        Produto produto = repository.findById(id);
        if (produto == null) {
            throw new NotFoundException("Produto não encontrado.");
        }
        return produto;
    }

    @Transactional
    public Produto inserir(Produto produto) {
        repository.persist(produto);
        return produto;
    }

    @Transactional
    public Produto atualizar(Long id, Produto produtoAtualizado) {
        Produto produto = repository.findById(id);

        if (produto == null) {
            throw new NotFoundException("Produto não encontrado.");
        }

        produto.setNome(produtoAtualizado.getNome());
        produto.setDescricao(produtoAtualizado.getDescricao());
        produto.setPreco(produtoAtualizado.getPreco());
        produto.setPesoGramas(produtoAtualizado.getPesoGramas());
        produto.setTipoProteina(produtoAtualizado.getTipoProteina());
        produto.setAtivo(produtoAtualizado.getAtivo());
        produto.setCategoria(produtoAtualizado.getCategoria());
        produto.setMarca(produtoAtualizado.getMarca());
        produto.setFornecedor(produtoAtualizado.getFornecedor());
        produto.setSabores(produtoAtualizado.getSabores());

        return produto;
    }

    @Transactional
    public void excluir(Long id) {
        Produto produto = repository.findById(id);

        if (produto == null) {
            throw new NotFoundException("Produto não encontrado.");
        }

        repository.delete(produto);
    }
}