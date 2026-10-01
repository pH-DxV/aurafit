package br.unitins.topicos1.aurafit.resource;

import java.net.URI;
import java.util.List;

import br.unitins.topicos1.aurafit.model.Marca;
import br.unitins.topicos1.aurafit.service.MarcaService;
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

@Path("/marcas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MarcaResource {

    @Inject
    MarcaService service;

    @GET
    public List<Marca> listar(@QueryParam("nome") String nome) {
        if (nome != null && !nome.isBlank()) {
            return service.buscarPorNome(nome);
        }
        return service.listarTodas();
    }

    @GET
    @Path("/{id}")
    public Marca buscarPorId(@PathParam("id") Long id) {
        return service.buscarPorId(id);
    }

    @POST
    public Response inserir(@Valid Marca marca) {
        Marca novaMarca = service.inserir(marca);
        return Response.created(URI.create("/marcas/" + novaMarca.getId()))
                .entity(novaMarca)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Marca atualizar(@PathParam("id") Long id, @Valid Marca marca) {
        return service.atualizar(id, marca);
    }

    @DELETE
    @Path("/{id}")
    public Response excluir(@PathParam("id") Long id) {
        service.excluir(id);
        return Response.noContent().build();
    }
}