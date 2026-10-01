package br.unitins.topicos1.aurafit.resource;

import java.net.URI;
import java.util.List;

import br.unitins.topicos1.aurafit.model.Sabor;
import br.unitins.topicos1.aurafit.service.SaborService;
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

@Path("/sabores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SaborResource {

    @Inject
    SaborService service;

    @GET
    public List<Sabor> listar(@QueryParam("nome") String nome) {
        if (nome != null && !nome.isBlank()) {
            return service.buscarPorNome(nome);
        }
        return service.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Sabor buscarPorId(@PathParam("id") Long id) {
        return service.buscarPorId(id);
    }

    @POST
    public Response inserir(@Valid Sabor sabor) {
        Sabor novoSabor = service.inserir(sabor);
        return Response.created(URI.create("/sabores/" + novoSabor.getId()))
                .entity(novoSabor)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Sabor atualizar(@PathParam("id") Long id, @Valid Sabor sabor) {
        return service.atualizar(id, sabor);
    }

    @DELETE
    @Path("/{id}")
    public Response excluir(@PathParam("id") Long id) {
        service.excluir(id);
        return Response.noContent().build();
    }
}