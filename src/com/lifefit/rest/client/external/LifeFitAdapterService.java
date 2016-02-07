package com.lifefit.rest.client.external;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Random;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import com.lifefit.rest.client.ss.LifeFitSSClient;
import com.lifefit.rest.model.QuoteStore;
import com.lifefit.rest.util.Transformer;
import com.owlike.genson.ext.jaxrs.GensonJsonConverter;

public class LifeFitAdapterService {

	static Response response;
	static String results = null;
	static InputStream stream = null;
	static QuoteStore quotes = null;
	
	WebTarget service;
	Client client;
	
	public LifeFitAdapterService(){
		init();
	}
	
	private void init(){
		final ClientConfig clientConfig = new ClientConfig().register(GensonJsonConverter.class);			
		client = ClientBuilder.newClient(clientConfig);		
	}
	
	private static URI getBaseURI(String SERVER_URL){
		return UriBuilder.fromUri(SERVER_URL).build();
	}
	
	public String getQuote(){
		String quote = null;
		String endpoint = null;
		Transformer transformer = new Transformer();
		Random rand = new Random();
		
		try{
			//get endpoint for STANDS4 Quote API
			LifeFitSSClient lifeFitSSClient = new LifeFitSSClient();
			endpoint = lifeFitSSClient.getAPIConfigByName("STANDS4");	
			service = client.target(getBaseURI(endpoint));
			
			response = service.path("").request().accept(MediaType.APPLICATION_XML).get();
			results = response.readEntity(String.class);	
			
			if(results != null){
				//Convert string into inputStream
				stream = new ByteArrayInputStream(results.getBytes(StandardCharsets.UTF_8));
				
				//Un-marshall quote xml String into quote object
				quotes = transformer.unmarshallXMLQuoteList(stream);
				
				//total obtained quotes
				int totalQuote = quotes.getData().size();
				
				//get a quote randomly
				quote = quotes.getData().get(rand.nextInt((totalQuote - 0) + 1) + 0).getQuote();
				quote = quote + "  ~" + quotes.getData().get(rand.nextInt((totalQuote - 0) + 1) + 0).getAuthor();
			}
		}
		catch(Exception e){e.printStackTrace();}		
		return quote;
	}
}
