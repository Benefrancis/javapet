package br.com.fiap.domain.resources;

import br.com.fiap.domain.entity.animal.Animal;
import br.com.fiap.domain.entity.animal.Cachorro;
import br.com.fiap.domain.service.AnimalService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/animal")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AnimalResource implements Resource<Cachorro, Long> {

    @Context
    UriInfo uriInfo;

    private AnimalService service = new AnimalService();

    @GET
    @Override
    public Response findAll() {
        List<Animal> all = service.findAll();
        return Response.ok(all).build();
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id") Long id) {
        Animal animal = service.findById(id);
        if (Objects.isNull(animal)) return Response.status(404).build();
        return Response.ok(animal).build();

    }

    @POST
    @Override
    public Response persiste(Cachorro animal) {

        Animal persisted = service.persiste(animal);

        if (Objects.isNull(persisted)) return Response.status(400).build();

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        URI uri = uriBuilder.path(String.valueOf(persisted.getId())).build();

        return Response.created(uri).entity(persisted).build();
    }
}
