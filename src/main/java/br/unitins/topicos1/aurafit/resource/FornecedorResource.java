package br.unitins.topicos1.aurafit.resource;

import java.net.URI;
import java.util.List;

import br.unitins.topicos1.aurafit.model.Fornecedor;
import br.unitins.topicos1.aurafit.service.FornecedorService;
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

@Path("/fornecedores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FornecedorResource {

    @Inject
    FornecedorService service;

    @GET
    public List<Fornecedor> listar(@QueryParam("razaoSocial") String razaoSocial) {
        if (razaoSocial != null && !razaoSocial.isBlank()) {
            return service.buscarPorRazaoSocial(razaoSocial);
        }
        return service.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Fornecedor buscarPorId(@PathParam("id") Long id) {
        return service.buscarPorId(id);
    }

    @POST
    public Response inserir(@Valid Fornecedor fornecedor) {
        Fornecedor novoFornecedor = service.inserir(fornecedor);
        return Response.created(URI.create("/fornecedores/" + novoFornecedor.getId()))
                .entity(novoFornecedor)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Fornecedor atualizar(@PathParam("id") Long id, @Valid Fornecedor fornecedor) {
        return service.atualizar(id, fornecedor);
    }

    @DELETE
    @Path("/{id}")
    public Response excluir(@PathParam("id") Long id) {
        service.excluir(id);
        return Response.noContent().build();
    }
}