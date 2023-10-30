package br.com.fiap.infra.configuration.cors;


import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
public class CORSFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        responseContext.getHeaders().putSingle( "Access-Control-Allow-Origin", "*" );
        responseContext.getHeaders().putSingle( "Access-Control-Allow-Credentials", "true" );
        responseContext.getHeaders().putSingle( "Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD" );
        responseContext.getHeaders().putSingle( "Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With" );
    }
}