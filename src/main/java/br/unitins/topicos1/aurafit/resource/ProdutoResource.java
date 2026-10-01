package br.unitins.topicos1.aurafit.resource;

import java.net.URI;
import java.util.List;

import br.unitins.topicos1.aurafit.model.Produto;
import br.unitins.topicos1.aurafit.model.TipoProteina;
import br.unitins.topicos1.aurafit.service.ProdutoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    @Inject
    ProdutoService service;

    @GET
    public List<Produto> listar(@QueryParam("nome") String nome,
                                @QueryParam("tipoProteina") TipoProteina tipoProteina) {

        if (nome != null && !nome.isBlank()) {
            return service.buscarPorNome(nome);
        }

        if (tipoProteina != null) {
            return service.buscarPorTipoProteina(tipoProteina);
        }

        return service.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Produto buscarPorId(@PathParam("id") Long id) {
        return service.buscarPorId(id);
    }

    @POST
    public Response inserir(@Valid Produto produto) {
        Produto novoProduto = service.inserir(produto);
        return Response.created(URI.create("/produtos/" + novoProduto.getId()))
                .entity(novoProduto)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Produto atualizar(@PathParam("id") Long id, @Valid Produto produto) {
        return service.atualizar(id, produto);
    }

    @DELETE
    @Path("/{id}")
    public Response excluir(@PathParam("id") Long id) {
        service.excluir(id);
        return Response.noContent().build();
    }
}