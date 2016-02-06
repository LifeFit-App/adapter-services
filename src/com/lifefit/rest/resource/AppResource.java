package com.lifefit.rest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.lifefit.rest.client.external.LifeFitAdapterService;

@Path("/externalapi")
public class AppResource {

	@GET
	@Path("quote")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String getQuote(){
    	LifeFitAdapterService client = new LifeFitAdapterService();  
        System.out.println("Get Quote from STANDS4 Quote API...");
    	
        String quote = client.getQuote();
        return quote;
    }
}
