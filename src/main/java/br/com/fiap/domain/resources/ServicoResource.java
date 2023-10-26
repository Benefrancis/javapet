package br.com.fiap.domain.resources;

import br.com.fiap.domain.entity.servico.Servico;
import br.com.fiap.domain.service.ServicoService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/servico")
public class ServicoResource implements Resource<Servico, Long> {

    @Context
    private UriInfo uriInfo;

    private ServicoService service = new ServicoService();

    @GET
    @Override
    public Response findAll() {
        List<Servico> all = service.findAll();
        return Response.ok( all ).build();
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id") Long id) {
        Servico servico = service.findById( id );
        if (Objects.isNull( servico )) return Response.status( 404 ).build();
        return Response.ok( servico ).build();
    }

    @POST
    @Override
    public Response persiste(Servico s) {

        Servico servico = service.persiste( s );
        if (Objects.isNull( servico )) return Response.status( 400 ).build();

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        URI uri = uriBuilder.path( String.valueOf( servico.getId() ) ).build();

        return Response.created( uri ).entity( servico ).build();
    }
}
