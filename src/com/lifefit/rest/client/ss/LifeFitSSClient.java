package com.lifefit.rest.client.ss;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import com.owlike.genson.ext.jaxrs.GensonJsonConverter;

public class LifeFitSSClient {

	static Response response;
	static String results = null;
	//RESTFul Web Service URL for LifeFit storage services
	final String SERVER_URL = "https://lifefit-ss-181499.herokuapp.com/lifefit-ss";
	WebTarget service;
	
	public LifeFitSSClient(){
		init();
	}
	
	private void init(){
		final ClientConfig clientConfig = new ClientConfig().register(GensonJsonConverter.class);			
		Client client = ClientBuilder.newClient(clientConfig);
		service = client.target(getBaseURI(SERVER_URL));		
	}
	
	private static URI getBaseURI(String SERVER_URL){
		return UriBuilder.fromUri(SERVER_URL).build();
	}
	
	public String getAPIConfigByName(String name){
		String endpoint = null;
		
		try{
			response = service.path("/externalapi/quote/"+name).request().accept(MediaType.APPLICATION_JSON).get();
			results = response.readEntity(String.class);			
			endpoint = results.toString();								
		}
		catch(Exception e){e.printStackTrace();}
		return endpoint;
	}
}
