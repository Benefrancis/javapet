package br.com.fiap.domain.resources;

import br.com.fiap.domain.entity.pessoa.PJ;
import br.com.fiap.domain.service.PJService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;


@Path("/pj")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PJResource implements Resource<PJ, Long> {

    @Context
    UriInfo uriInfo;

    PJService service = new PJService();

    @GET
    @Override
    public Response findAll() {
        List<PJ> all = service.findAll();
        return Response.ok( all ).build();
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id") Long id) {
        PJ pj = service.findById( id );
        if (Objects.isNull( pj )) return Response.status( 404 ).build();
        return Response.ok( pj ).build();
    }

    @POST
    @Override
    public Response persiste(PJ pj) {
        pj.setId( null );
        PJ pessoa = service.persiste( pj );

        if (Objects.isNull( pessoa ))
            return Response.notModified( "Não foi possível salvar a Pessoa Jurírica " + pj ).build();

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        URI uri = uriBuilder.path( String.valueOf( pessoa.getId() ) ).build();

        return Response.created( uri ).entity( pessoa ).build();
    }


}
