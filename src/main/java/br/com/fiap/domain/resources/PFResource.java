package br.com.fiap.domain.resources;

import br.com.fiap.domain.entity.pessoa.PF;
import br.com.fiap.domain.service.PFService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("pf/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PFResource implements Resource<PF, Long>{

    @Context
    UriInfo uriInfo;
    PFService service = new PFService();

    @GET
    @Override
    public Response findAll() {
        List<PF> all = service.findAll();
        return Response.ok(all).build();
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id") Long id) {

        PF pf = service.findById(id);

        if (Objects.isNull(pf)) return Response.status(404).build();


        return Response.ok(pf).build();
    }

    @Override
    public Response findByTexto(String texto) {
        return null;
    }

    @POST
    @Override
    public Response persiste(PF pf) {
        pf.setId(null);
        PF pessoa = service.persiste(pf);

        if (Objects.isNull(pessoa.getId())) return Response.notModified("Não foi possivel persistir: " + pf).build();

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();

        URI uri = uriBuilder.path(String.valueOf(pessoa.getId())).build();


        return Response.created(uri).entity(pessoa).build();
    }

    @Override
    public Response update(Long id, PF pf) {
        return null;
    }

    @Override
    public Response delete(Long id) {
        return null;
    }
}
