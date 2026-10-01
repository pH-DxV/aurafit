package br.unitins.topicos1.aurafit.resource;

import java.net.URI;
import java.util.List;

import br.unitins.topicos1.aurafit.model.Categoria;
import br.unitins.topicos1.aurafit.service.CategoriaService;
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

@Path("/categorias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoriaResource {

    @Inject
    CategoriaService service;

    @GET
    public List<Categoria> listar(@QueryParam("nome") String nome) {
        if (nome != null && !nome.isBlank()) {
            return service.buscarPorNome(nome);
        }
        return service.listarTodas();
    }

    @GET
    @Path("/{id}")
    public Categoria buscarPorId(@PathParam("id") Long id) {
        return service.buscarPorId(id);
    }

    @POST
    public Response inserir(@Valid Categoria categoria) {
        Categoria novaCategoria = service.inserir(categoria);
        return Response.created(URI.create("/categorias/" + novaCategoria.getId()))
                .entity(novaCategoria)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Categoria atualizar(@PathParam("id") Long id, @Valid Categoria categoria) {
        return service.atualizar(id, categoria);
    }

    @DELETE
    @Path("/{id}")
    public Response excluir(@PathParam("id") Long id) {
        service.excluir(id);
        return Response.noContent().build();
    }
}