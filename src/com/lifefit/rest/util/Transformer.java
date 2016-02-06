package com.lifefit.rest.util;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.lifefit.rest.model.QuoteStore;

public class Transformer {

	public QuoteStore unmarshallXMLQuoteList(InputStream stream){		
		QuoteStore quote = null;
		try{
			JAXBContext jc = JAXBContext.newInstance(QuoteStore.class);    			
			//Un-marshalling from xml String to QuoteStore
    		Unmarshaller um = jc.createUnmarshaller();
    		quote = (QuoteStore) um.unmarshal(stream);    		
		}
		catch(Exception e){e.printStackTrace();}		
		return quote;		
	}
}
